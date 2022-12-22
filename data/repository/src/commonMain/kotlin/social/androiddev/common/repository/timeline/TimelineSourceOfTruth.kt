/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.repository.timeline

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import org.mobilenativefoundation.store.store5.SourceOfTruth
import social.androiddev.common.timeline.StatusDB
import social.androiddev.common.timeline.TimelineDatabase
import social.androiddev.common.timeline.TimelineQueries
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.model.StatusLocal

fun TimelineDatabase.asSourceOfTruth(): SourceOfTruth<FeedType, List<StatusDB>, List<StatusLocal>> =
    SourceOfTruth.of(
        reader = reader(),
        writer = { key, input: List<StatusDB> ->
            input.forEach { item -> tryWriteItem(item, key) }
        }
    )

private fun TimelineDatabase.reader() = { key: FeedType ->
    when (key) {
        is FeedType.Home ->
            timelineQueries.homeItemsAsLocal(key)
    }
}

private fun TimelineQueries.homeItemsAsLocal(key: FeedType) = selectHomeItems()
    .asFlow()
    .mapToList()
    .map {
        it.ifEmpty { return@map null } // treat empty list as no result otherwise
        it.map { item -> item.toLocal(key) }
    }

fun TimelineDatabase.tryWriteItem(it: StatusDB, type: FeedType): Boolean = try {
    timelineQueries.insertFeedItem(
        it.copy(type = type.type)
    )
    true
} catch (t: Throwable) {
    throw RuntimeException(t)
}
