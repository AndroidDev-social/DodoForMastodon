package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/source/
 */
@Serializable
data class Source(
    // base attributes
    @SerialName("note") val note: String,
    @SerialName("fields") val fields: List<Field>,

    // optional attributes
    @SerialName("privacy") val privacy: Privacy,
    @SerialName("sensitive") val sensitive: Boolean,
    @SerialName("language") val language: String,
    @SerialName("follow_requests_count") val followRequestsCount: Int,
)

enum class Privacy { public, unlisted, private, direct }
