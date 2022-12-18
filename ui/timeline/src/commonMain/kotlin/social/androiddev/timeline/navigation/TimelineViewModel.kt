package social.androiddev.timeline.navigation

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.store5.ResponseOrigin
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.HomeTimelineRepository
import social.androiddev.domain.timeline.model.StatusUI
import kotlin.coroutines.CoroutineContext

class TimelineViewModel(
    private val mainContext: CoroutineContext,
    private val homeTimelineRepository: HomeTimelineRepository
) : InstanceKeeper.Instance {
    private val scope = CoroutineScope(mainContext + SupervisorJob())
    private val _state = MutableStateFlow<StoreResponse<List<StatusUI>>>(StoreResponse.Loading(ResponseOrigin.SourceOfTruth))
    val state: StateFlow<StoreResponse<List<StatusUI>>> = _state.asStateFlow()
    init {
        scope.launch {
           try {
               homeTimelineRepository.read(FeedType.Home, refresh = true).collect{
                   _state.value = it
               }
           } catch (e:Exception){
               e.message
           }

        }
    }
    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }

}