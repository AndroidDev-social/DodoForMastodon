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
import social.androiddev.domain.timeline.model.StatusLocal
import social.androiddev.timeline.FeedItemState
import kotlin.coroutines.CoroutineContext

class TimelineViewModel(
    private val mainContext: CoroutineContext,
    private val homeTimelineRepository: HomeTimelineRepository,
    private val feedType: FeedType
) : InstanceKeeper.Instance {
    private val scope = CoroutineScope(mainContext + SupervisorJob())
    private val _state =
        MutableStateFlow<StoreResponse<List<FeedItemState>>>(StoreResponse.Loading(ResponseOrigin.SourceOfTruth))
    val state: StateFlow<StoreResponse<List<FeedItemState>>> = _state.asStateFlow()

    init {
        scope.launch {
            homeTimelineRepository.read(refresh = true).collect {
                when (val response: StoreResponse<List<StatusLocal>> = it) {
                    is StoreResponse.Data -> {
                        val result = StoreResponse.Data(response.value.map {
                            FeedItemState(
                                id = it.remoteId,
                                userAvatarUrl = it.avatarUrl,
                                date = it.createdAt,
                                username = it.userName,
                                acctAddress = it.accountAddress,
                                message = it.content,
                                images = emptyList(),
                                videoUrl = null,
                            )
                        }, response.origin)
                        _state.value = result
                    }

                    else -> {
                        //TODO display error/loading
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }

}