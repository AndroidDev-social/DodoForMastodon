package social.androiddev.common.repository.timeline

import social.androiddev.common.network.model.Status
import social.androiddev.common.timeline.StatusDB
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.model.StatusLocal

fun StatusDB.toLocal(
    key: FeedType
) = StatusLocal(
    remoteId = remoteId,
    feedType = key,
    createdAt = createdAt,
    repliesCount = repliesCount,
    reblogsCount = favouritesCount,
    favoritesCount = favouritesCount,
    content = content,
    sensitive = sensitive ?: false,
    spoilerText = spoilerText,
    visibility = visibility,
    avatarUrl = avatarUrl,
    accountAddress = accountAddress,
    userName = userName
)


fun Status.statusDB() =
    StatusDB(
        type = FeedType.Home.type,
        remoteId = id,
        uri = uri,
        createdAt = createdAt,
        content = content,
        accountId = account?.id,
        visibility = visibility.name,
        sensitive = sensitive,
        spoilerText = spoilerText,
        applicationName = application?.name ?: "",
        repliesCount = repliesCount?.toLong(),
        reblogsCount = reblogsCount?.toLong(),
        favouritesCount = favouritesCount?.toLong(),
        avatarUrl = account?.avatar?:"",
        accountAddress = account?.acct?:"",
        userName = account?.username?:" "
    )