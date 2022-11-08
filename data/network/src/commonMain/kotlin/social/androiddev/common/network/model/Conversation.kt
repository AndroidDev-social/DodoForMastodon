package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/conversation/
 */
@Serializable
data class Conversation(
    // required attributes
    @SerialName("id") val id: String,
    @SerialName("accounts") val accounts: List<Account>,
    @SerialName("unread") val unread: Boolean,

    // optional attributes
    @SerialName("last_status") val lastStatus: Status,
)
