package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/application/
 */
@Serializable
data class Application(
    // required attributes
    @SerialName("name") val name: String,
    // optional attributes
    @SerialName("website") val website: String? = null,
    @SerialName("vapid_key") val vapidKey: String? = null,

    // client attributes
    @SerialName("client_id") val clientId: String? = null,
    @SerialName("client_secret") val clientSecret: String? = null
)
