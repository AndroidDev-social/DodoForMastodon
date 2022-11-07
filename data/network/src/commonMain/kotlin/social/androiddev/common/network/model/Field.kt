package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/field/
 */
@Serializable
data class Field(
    @SerialName("name") val name: String,
    @SerialName("value") val value: String,
    @SerialName("verified_at") val verifiedAt: String? = null
)
