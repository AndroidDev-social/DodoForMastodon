package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/emoji/
 */
@Serializable
data class Emoji(
    // required attributes
    @SerialName("shortcode") val shortcode: String,
    @SerialName("url") val url: String,
    @SerialName("static_url") val staticUrl: String,
    @SerialName("visible_in_picker") val visibleInPicker: Boolean,

    // optional attributes
    @SerialName("category") val category: String? = null
)
