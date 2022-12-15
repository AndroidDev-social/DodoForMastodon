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
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobilenativefoundation.store.store5.MarketResponse
import social.androiddev.common.repository.timeline.HomeTimelineRepository
import social.androiddev.common.timeline.TimelineItem
import kotlin.coroutines.CoroutineContext

/**
 * The base component describing all business logic needed for the timeline view
 */
interface TimelineComponent{
    val state: StateFlow<MarketResponse<List<TimelineItem>>>
}

class DefaultTimelineComponent(
    mainContext: CoroutineContext,
    private val componentContext: ComponentContext,
) : TimelineComponent, KoinComponent, ComponentContext by componentContext {

    private val homeTimelineRepository:HomeTimelineRepository by inject()

    private val viewModel = instanceKeeper.getOrCreate {
        TimelineViewModel(
            mainContext = mainContext,
            homeTimelineRepository
        )
    }

    override val state: StateFlow<MarketResponse<List<TimelineItem>>> = viewModel.state

}

class TimelineViewModel(
    private val mainContext: CoroutineContext,
    private val homeTimelineRepository: HomeTimelineRepository
) : InstanceKeeper.Instance {
    private val scope = CoroutineScope(mainContext + SupervisorJob())
    private val _state = MutableStateFlow<MarketResponse<List<TimelineItem>>>(MarketResponse.Empty)
    val state: StateFlow<MarketResponse<List<TimelineItem>>> = _state.asStateFlow()
    init {
        scope.launch {
            homeTimelineRepository.read().collect{
               _state.value = it
            }

        }
    }
    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }

}
