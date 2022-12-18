/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.repository.timeline

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersHolder
import org.koin.dsl.module
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import social.androiddev.common.network.MastodonApi
import social.androiddev.common.network.model.Status
import social.androiddev.common.persistence.localstorage.DodoAuthStorage
import social.androiddev.common.timeline.TimelineDatabase
import social.androiddev.common.timeline.TimelineItem
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.HomeTimelineRepository
import social.androiddev.domain.timeline.model.StatusUI

/**
 * Koin module containing all koin/bean definitions for
 * timeline repository delegates.
 */
val timelineRepoModule: Module = module {

    factory<HomeTimelineRepository> { RealHomeTimelineRepository(get()) }

    factory<SourceOfTruth<FeedType, List<TimelineItem>, List<StatusUI>>> { it: ParametersHolder ->
        val database = get<TimelineDatabase>()

        SourceOfTruth.of(
            reader = { key: FeedType ->
                when (key) {
                    is FeedType.Home -> get<TimelineDatabase>()
                        .timelineQueries
                        .selectHomeItems()
                        .asFlow()
                        .mapToList()
                        .map {
                            it.ifEmpty {
                                return@map null
                            }
                            it.map { item ->
                                StatusUI(
                                    remoteId = item.remoteId,
                                    feedType = key,
                                    createdAt = item.createdAt,
                                    repliesCount = item.repliesCount,
                                    reblogsCount = item.favouritesCount,
                                    favoritesCount = item.favouritesCount,
                                    content = item.content,
                                    sensitive = item.sensitive ?: false,
                                    spoilerText = item.spoilerText,
                                    visibility = item.visibility,
                                    avatarUrl = item.avatarUrl,
                                    accountAddress =  item.accountAddress,
                                    userName = item.userName
                                )
                            }
                        }
                }
            },
            writer = { key, input ->
                input.forEach { database.tryWriteItem(it, key) }
            }
        )
    }

    factory<Fetcher<FeedType, List<TimelineItem>>> {
        Fetcher.of { key: FeedType ->
            when (key) {
                is FeedType.Home -> {
                    val authStorage = get<DodoAuthStorage>()
                    get<MastodonApi>()
                        .getHomeFeed(
                            authStorage.currentDomain!!,
                            authStorage.getAccessToken(authStorage.currentDomain!!)!!
                        )
                        .getOrThrow()
                        .map(::timelineItem)
                }
            }
        }
    }

    factory {
        val fetcher = get<Fetcher<FeedType, List<TimelineItem>>>()
        val sourceOfTruth = get<SourceOfTruth<FeedType, List<TimelineItem>, List<StatusUI>>>()
        StoreBuilder
            .from(
                fetcher = fetcher,
                sourceOfTruth = sourceOfTruth
            )
            .build()
    }
}

private fun timelineItem(it: Status) =
    TimelineItem(
        type = FeedType.Home.type,
        remoteId = it.id,
        uri = it.uri,
        createdAt = it.createdAt,
        content = it.content,
        accountId = it.account?.id,
        visibility = it.visibility.name,
        sensitive = it.sensitive,
        spoilerText = it.spoilerText,
        applicationName = it.application?.name ?: "",
        repliesCount = it.repliesCount?.toLong(),
        reblogsCount = it.reblogsCount?.toLong(),
        favouritesCount = it.favouritesCount?.toLong(),
        avatarUrl = it.account?.avatar?:"",
        accountAddress = it.account?.acct?:"",
        userName = it.account?.username?:" "
    )


fun TimelineDatabase.tryWriteItem(it: TimelineItem, type: FeedType): Boolean = try {
    timelineQueries.insertFeedItem(
        type = type.type,
        remoteId = it.remoteId,
        uri = it.uri,
        createdAt = it.createdAt,
        content = it.content,
        accountId = it.accountId,
        visibility = it.visibility,
        sensitive = it.sensitive,
        spoilerText = it.spoilerText,
        applicationName = it.applicationName,
        repliesCount = it.repliesCount,
        favouritesCount = it.favouritesCount,
        reblogsCount = it.reblogsCount,
        avatarUrl = it.avatarUrl,
        accountAddress = it.accountAddress,
        userName = it.userName
    )
    true
} catch (t: Throwable) {
    throw RuntimeException(t)
}

