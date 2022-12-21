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

import org.koin.core.module.Module
import org.koin.dsl.module
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.StoreBuilder
import social.androiddev.common.network.MastodonApi
import social.androiddev.common.timeline.StatusDB
import social.androiddev.common.timeline.TimelineDatabase
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.HomeTimelineRepository
import social.androiddev.domain.timeline.model.StatusLocal

/**
 * Koin module containing all koin/bean definitions for
 * timeline repository delegates.
 */
val timelineRepoModule: Module = module {

    factory<HomeTimelineRepository> { RealHomeTimelineRepository(get()) }

    factory { get<TimelineDatabase>().asSourceOfTruth() }

    factory { get<MastodonApi>().timelineFetcher(authStorage = get()) }

    factory {
        val fetcher = get<Fetcher<FeedType, List<StatusDB>>>()
        val sourceOfTruth = get<SourceOfTruth<FeedType, List<StatusDB>, List<StatusLocal>>>()
        StoreBuilder.from(
            fetcher = fetcher,
            sourceOfTruth = sourceOfTruth
        )
            .build()
    }
}
