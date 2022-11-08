package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *https://docs.joinmastodon.org/entities/status/
 */
@Serializable
data class Status(
    // base attributes
    @SerialName("id") val id: String,
    @SerialName("uri") val uri: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("account") val account: Account,
    @SerialName("content") val content: String,
    @SerialName("visibility") val visibility: Privacy,
    @SerialName("sensitive") val sensitive: Boolean,
    @SerialName("spoiler_text") val spoilerText: String,
    @SerialName("media_attachments") val mediaAttachments: List<Attachment>,
    @SerialName("application") val application: Application,

    // rendering attributes
    @SerialName("mentions") val mentions: List<Mention>? = null,
    @SerialName("tags") val tags: List<Tag>? = null,
    @SerialName("emojis") val emojis: List<Emoji>? = null,

    // informal attributes
    @SerialName("reblogs_count") val reblogsCount: Int? = null,
    @SerialName("favourites_count") val favouritesCount: Int? = null,
    @SerialName("replies_count") val repliesCount: Int? = null,

    // nullable attributes
    @SerialName("url") val url: String? = null,
    @SerialName("in_reply_to_id") val inReplyToId: String? = null,
    @SerialName("in_reply_to_account_id") val inReplyToAccountId: String? = null,
    @SerialName("reblog") val reblog: Status? = null,
    @SerialName("poll") val poll: Poll? = null,
    @SerialName("card") val card: Card? = null,
    @SerialName("language") val language: String? = null,
    @SerialName("text") val text: String? = null,

    // Authorized user attributes
    @SerialName("favourited") val favourited: Boolean? = null,
    @SerialName("reblogged") val reblogged: Boolean? = null,
    @SerialName("muted") val muted: Boolean? = null,
    @SerialName("bookmarked") val bookmarked: Boolean? = null,
    @SerialName("pinned") val pinned: Boolean? = null,
)
