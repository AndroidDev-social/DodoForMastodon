package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/instance/
 */
@Serializable
data class Instance(
    @SerialName("uri") val uri: String,
    @SerialName("title") val title: String,
    @SerialName("description") val description: String,
    @SerialName("short_description") val shortDescription: String,
    @SerialName("email") val email: String,
    @SerialName("version") val version: String,
    @SerialName("languages") val languages: List<String>,
    @SerialName("registrations") val registrations: Boolean,
    @SerialName("approval_required") val approvalRequired: Boolean,
    @SerialName("invites_enabled") val invitesEnabled: Boolean,
    @SerialName("urls") val urls: Map<String, String>,
    @SerialName("stats") val stats: Map<String, Int>,
    @SerialName("thumbnail") val thumbnail: String?,
    @SerialName("contact_account") val contactAccount: Account,
)
