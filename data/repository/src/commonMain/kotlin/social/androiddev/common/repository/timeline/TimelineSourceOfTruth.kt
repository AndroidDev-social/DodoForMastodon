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
        writer = { key, input ->
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
        it.ifEmpty { return@map null } //treat empty list as no result otherwise
        it.map { item -> item.toLocal(key) }
    }

fun TimelineDatabase.tryWriteItem(it: StatusDB, type: FeedType): Boolean = try {
    timelineQueries.insertFeedItem(
        type = type.type,
        remoteId = it.remoteId,
        uri = it.uri,
        createdAt = it.createdAt,
        content = it.content,
        accountId = it.accountId,
        visibility = it.visibility,
        sensitive = it.sensitive,
        spoilerText = it.spoilerText,
        applicationName = it.applicationName,
        repliesCount = it.repliesCount,
        favouritesCount = it.favouritesCount,
        reblogsCount = it.reblogsCount,
        avatarUrl = it.avatarUrl,
        accountAddress = it.accountAddress,
        userName = it.userName
    )
    true
} catch (t: Throwable) {
    throw RuntimeException(t)
}
