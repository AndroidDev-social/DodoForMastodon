package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/pushsubscription/
 */
@Serializable
data class PushSubscription(
    // required attributes
    @SerialName("id") val id: String,
    @SerialName("endpoint") val endpoint: String,
    @SerialName("server_key") val serverKey: String,
    @SerialName("alerts") val alerts: Alerts,
)

@Serializable
data class Alerts(
    val follow: Boolean,
    val favourite: Boolean,
    val mention: Boolean,
    val reblog: Boolean,
    val poll: Boolean,
)
