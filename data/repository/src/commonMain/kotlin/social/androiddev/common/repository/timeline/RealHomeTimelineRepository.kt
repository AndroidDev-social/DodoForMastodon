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
package social.androiddev.common.repository.timeline

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreRequest
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.HomeTimelineRepository
import social.androiddev.domain.timeline.model.StatusLocal

class RealHomeTimelineRepository(
    private val store: Store<FeedType, List<StatusLocal>>
) : HomeTimelineRepository {
    /**
     * returns a flow of home feed items from a database
     * anytime table rows are created/updated will return a new list of timeline items
     * on first return will also call network fetcher to get
     * latest from network and update local storage with it]
     */
    override fun read(
        feedType: FeedType,
        refresh: Boolean
    ): Flow<StoreResponse<List<StatusLocal>>> {
        return store.stream(StoreRequest.cached(key = feedType, refresh = true))
            .distinctUntilChanged()
    }
}
