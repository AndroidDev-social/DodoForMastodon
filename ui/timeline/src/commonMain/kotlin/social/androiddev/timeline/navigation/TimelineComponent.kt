package social.androiddev.timeline.navigation

import kotlinx.coroutines.flow.StateFlow
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.timeline.FeedItemState

/**
 * The base component describing all business logic needed for the timeline view
 */
interface TimelineComponent {
    val state: StateFlow<StoreResponse<List<FeedItemState>>>
}