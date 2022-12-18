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
