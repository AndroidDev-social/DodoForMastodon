/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.timeline.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.HomeTimelineRepository
import social.androiddev.timeline.FeedItemState
import kotlin.coroutines.CoroutineContext



class DefaultTimelineComponent(
    mainContext: CoroutineContext,
    private val componentContext: ComponentContext,
) : TimelineComponent, KoinComponent, ComponentContext by componentContext {

    private val homeTimelineRepository: HomeTimelineRepository by inject()

    private val viewModel = instanceKeeper.getOrCreate {
        TimelineViewModel(
            mainContext = mainContext,
            homeTimelineRepository,
            FeedType.Home
        )
    }

    override val state: StateFlow<StoreResponse<List<FeedItemState>>> = viewModel.state

}

