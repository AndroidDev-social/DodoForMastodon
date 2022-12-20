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

import org.mobilenativefoundation.store.store5.Fetcher
import social.androiddev.common.network.MastodonApi
import social.androiddev.common.persistence.localstorage.DodoAuthStorage
import social.androiddev.common.timeline.StatusDB
import social.androiddev.domain.timeline.FeedType

/**
 * Wrapper for [MastodonApi.getHomeFeed] while also getting an auth token from storage
 * and mapping result to list of [StatusDB]
 */

fun MastodonApi.timelineFetcher(authStorage: DodoAuthStorage): Fetcher<FeedType, List<StatusDB>> =
    Fetcher.of { key: FeedType ->
        when (key) {
            is FeedType.Home -> {
                getHomeFeed(
                    authStorage.currentDomain!!,
                    authStorage.getAccessToken(authStorage.currentDomain!!)!!
                )
                    .getOrThrow()
                    .map { it.statusDB() }
            }
        }
    }
