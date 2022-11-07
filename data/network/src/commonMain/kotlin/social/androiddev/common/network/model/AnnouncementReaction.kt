package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/announcementreaction/
 */
@Serializable
data class AnnouncementReaction(
    // base attributes
    @SerialName("name") val name: String,
    @SerialName("count") val count: Int,
    @SerialName("me") val me: Boolean,

    // custom emoji attributes
    @SerialName("url") val url: String? = null,
    @SerialName("static_url") val staticUrl: String? = null,
)
