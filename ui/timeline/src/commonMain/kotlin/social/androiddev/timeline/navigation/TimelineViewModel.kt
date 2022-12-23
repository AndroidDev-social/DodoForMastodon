/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.timeline.navigation

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import org.mobilenativefoundation.store.store5.ResponseOrigin
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.HomeTimelineRepository
import social.androiddev.domain.timeline.model.StatusLocal
import social.androiddev.timeline.FeedItemState
import social.androiddev.timeline.ImmutableListWrapper
import kotlin.coroutines.CoroutineContext

class TimelineViewModel(
    mainContext: CoroutineContext,
    homeTimelineRepository: HomeTimelineRepository,
    feedType: FeedType
) : InstanceKeeper.Instance {

    private val scope = CoroutineScope(mainContext + SupervisorJob())

    // TODO@OMID - Add kotlin compiler args
    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<StoreResponse<List<FeedItemState>>> = homeTimelineRepository
        .read(feedType, refresh = true)
        .mapLatest(::render)
        .stateIn(scope, SharingStarted.Eagerly, StoreResponse.Loading(ResponseOrigin.Cache))

    private fun render(response: StoreResponse<List<StatusLocal>>): StoreResponse.Data<List<FeedItemState>> {
        return when (response) {
            is StoreResponse.Data -> {
                val result = StoreResponse.Data(
                    response.value.map {
                        FeedItemState(
                            id = it.remoteId,
                            userAvatarUrl = it.avatarUrl,
                            date = it.createdAt,
                            username = it.userName,
                            acctAddress = it.accountAddress,
                            message = it.content,
                            images = ImmutableListWrapper.empty(),
                            videoUrl = null,
                        )
                    },
                    response.origin
                )
                result
            }

            else -> {
                StoreResponse.Data(emptyList(), response.origin)
            }
        }
    }

    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }
}
