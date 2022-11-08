package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/preferences/
 */
@Serializable
data class Preferences(
    // required attributes
    @SerialName("posting:default:visibility") val postingVisibility: PostingVisibility,
    @SerialName("posting:default:sensitive") val postingSensitive: Boolean,
    @SerialName("posting:default:language") val postingLanguage: String? = null,
    @SerialName("reading:expand:media") val readingMedia: ReadingMedia,
    @SerialName("reading:expand:spoilers") val readingSpoilers: Boolean,

    // optional attributes
    @SerialName("history") val history: List<History>? = null,
)

enum class PostingVisibility {
    public, unlisted, private, direct
}

enum class ReadingMedia {
    default, show_all, hide_all
}
