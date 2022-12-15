package social.androiddev.common.repository.timeline

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreRequest
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.HomeTimelineRepository
import social.androiddev.domain.timeline.model.StatusUI


class RealHomeTimelineRepository(
    private val store: Store<FeedType, List<StatusUI>>
) : HomeTimelineRepository {
    /**
     * returns a flow of home feed items from a database
     * anytime table rows are created/updated will return a new list of timeline items
     * on first return will also call network fetcher to get
     * latest from network and update local storage with it]
     */
    override suspend fun read(
        feedType: FeedType,
        refresh: Boolean
    ): Flow<StoreResponse<List<StatusUI>>> {
        return store.stream(StoreRequest.cached(key = feedType, refresh = true))
            .distinctUntilChanged()    }


}

