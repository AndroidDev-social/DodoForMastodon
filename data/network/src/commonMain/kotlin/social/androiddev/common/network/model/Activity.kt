package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/activity/
 */
@Serializable
data class Activity(
    @SerialName("week") val week: String,
    @SerialName("statuses") val statuses: String,
    @SerialName("logins") val logins: String,
    @SerialName("registrations") val registrations: String,
)
