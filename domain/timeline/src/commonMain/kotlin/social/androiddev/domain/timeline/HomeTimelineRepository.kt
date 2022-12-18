package social.androiddev.domain.timeline

import kotlinx.coroutines.flow.Flow

import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.domain.timeline.model.StatusLocal

interface HomeTimelineRepository {
    suspend fun read(feedType: FeedType, refresh: Boolean = false): Flow<StoreResponse<List<StatusLocal>>>
}
sealed class FeedType(val type:String){
    object Home: FeedType("HOME")
}