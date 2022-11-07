package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/emoji/
 */
@Serializable
data class Tag(
    // required attributes
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,

    // optional attributes
    @SerialName("history") val history: List<History>,
)
