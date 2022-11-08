package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/notification/
 */
@Serializable
data class Notification(
    // required attributes
    @SerialName("id") val id: String,
    @SerialName("type") val type: NotificationType,
    @SerialName("created_at") val createdAt: String,
    @SerialName("account") val account: Account,

    // optional attributes
    @SerialName("status") val status: Status,
)

enum class NotificationType {
    follow, follow_request, mention, reblog, favourite, poll, status
}
