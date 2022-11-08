package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/filter/
 */
@Serializable
data class Filter(
    @SerialName("id") val id: String,
    @SerialName("phrase") val phrase: String,
    @SerialName("context") val context: List<FilterContext>,
    @SerialName("expires_at") val expiresAt: String,
    @SerialName("irreversible") val irreversible: Boolean,
    @SerialName("whole_word") val wholeWord: Boolean,

)

enum class FilterContext {
    home, notifications, public, thread
}
