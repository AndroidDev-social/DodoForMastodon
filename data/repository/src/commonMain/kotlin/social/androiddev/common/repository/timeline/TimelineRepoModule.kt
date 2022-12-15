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
import org.koin.dsl.module
import org.mobilenativefoundation.store.store5.Bookkeeper
import org.mobilenativefoundation.store.store5.Market
import org.mobilenativefoundation.store.store5.NetworkFetcher
import org.mobilenativefoundation.store.store5.NetworkUpdater
import org.mobilenativefoundation.store.store5.OnNetworkCompletion
import org.mobilenativefoundation.store.store5.Store
import social.androiddev.common.network.MastodonApi
import social.androiddev.common.network.model.Status
import social.androiddev.common.persistence.localstorage.DodoAuthStorage
import social.androiddev.common.timeline.TimelineDatabase
import social.androiddev.common.timeline.TimelineItem

/**
 * Koin module containing all koin/bean definitions for
 * timeline repository delegates.
 */
val timelineRepoModule: Module = module {

    factory<HomeTimelineRepository> { RealHomeTimelineRepository(get()) }

    factory<Store<FeedType, List<TimelineItem>, List<TimelineItem>>> {
        val database = get<TimelineDatabase>()
        Store.by(
            reader = { key: FeedType ->
                when(key){
                    is FeedType.Home ->  get<TimelineDatabase>()
                        .timelineQueries
                        .selectHomeItems()
                        .asFlow()
                        .mapToList().map {
                            it.ifEmpty { return@map null }
                        }
                }

            },
            writer = { _, input ->
                input.forEach(database::tryWriteItem)
                true
            },
            deleter = { TODO() },
            clearer = { TODO() }
        )
    }
    factory {
        //Todo Add logic for conflict resolution handling for when we start posting toots
        Bookkeeper.by(
            read = { _: FeedType -> null },
            write = { _, _ -> true },
            delete = { TODO() },
            deleteAll = { TODO() }
        )
    }

    factory {
        NetworkFetcher.by(
            get = { key: FeedType ->
                when(key) {
                    is FeedType.Home -> {
                        val authStorage = get<DodoAuthStorage>()
                        get<MastodonApi>()
                            .getHomeFeed(authStorage.currentDomain!!, authStorage.getAccessToken(authStorage.currentDomain!!)!!)
                            .getOrThrow()
                            .map(::timelineItem)
                    }
                }
            },
            post = { key, item -> TODO() },
            converter = { it }
        )
    }


    factory {
        NetworkUpdater.by(
            post = { key: FeedType, _: List<Status> ->
                get<MastodonApi>()
                TODO()
            },
            onCompletion = OnNetworkCompletion(
                onSuccess = {},
                onFailure = {}
            ),
            converter = { TODO() }
        )
    }

    factory<Market<FeedType, List<TimelineItem>, List<TimelineItem>>> {
        Market.of<FeedType, List<TimelineItem>, List<TimelineItem>>(
            stores = listOf(get()), //TODO MIKE: ADD memory cache
            bookkeeper = get(),
            fetcher = get(),
            updater = get()
        )
    }
}

private fun timelineItem(it: Status) =
    TimelineItem(it.id, FeedType.Home.type, it.createdAt)

fun TimelineDatabase.tryWriteItem(timelineItem: TimelineItem): Boolean = try {
    timelineQueries.insertFeedItem(timelineItem)
    true
} catch (t: Throwable) {
    throw RuntimeException(t)
}

sealed class FeedType(val type:String){
    object Home: FeedType("HOME")
}