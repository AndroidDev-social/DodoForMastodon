package social.androiddev.common.repository.timeline

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.mobilenativefoundation.store.store5.Market
import org.mobilenativefoundation.store.store5.MarketResponse
import org.mobilenativefoundation.store.store5.ReadRequest
import social.androiddev.common.timeline.TimelineItem

interface HomeTimelineRepository {
    suspend fun read(): Flow<MarketResponse<List<TimelineItem>>>
}

class RealHomeTimelineRepository(
    private val market:   Market<FeedType, List<TimelineItem>, List<TimelineItem>>
) : HomeTimelineRepository {
    /**
     * returns a flow of home feed items from a database
     * anytime table rows are created/updated will return a new list of timeline items
     * on first return will also call network fetcher to get
     * latest from network and update local storage with it]
     */


    override suspend fun read(): Flow<MarketResponse<List<TimelineItem>>> {
        return market.read(ReadRequest.of(
            FeedType.Home,
            emptyList(),
            null,
            true))
            .distinctUntilChanged()
    }


}


