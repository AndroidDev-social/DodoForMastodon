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

import social.androiddev.common.network.model.Status
import social.androiddev.common.timeline.StatusDB
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.model.StatusLocal
import social.androiddev.domain.timeline.model.Visibility

fun StatusDB.toLocal(
    key: FeedType
) = StatusLocal(
    remoteId = remoteId,
    feedType = key,
    createdAt = createdAt,
    repliesCount = repliesCount ?: 0,
    reblogsCount = favouritesCount ?: 0,
    favoritesCount = favouritesCount ?: 0,
    content = content,
    sensitive = sensitive ?: false,
    spoilerText = spoilerText,
    visibility = Visibility.valueOf(visibility),
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
        avatarUrl = account?.avatar ?: "",
        accountAddress = account?.acct ?: "",
        userName = account?.username ?: " "
    )
