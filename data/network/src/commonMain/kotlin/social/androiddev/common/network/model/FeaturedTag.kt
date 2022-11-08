package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/featuredtag/
 */
@Serializable
data class FeaturedTag(
    // required attributes
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("statuses_count") val statusesCount: Int,
    @SerialName("last_status_at") val lastStatusAt: String,

    // optional attributes
    @SerialName("history") val history: List<History>? = null,
)
