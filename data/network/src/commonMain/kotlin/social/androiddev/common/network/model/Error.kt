package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/error/
 */
@Serializable
data class Error(
    // required attributes
    @SerialName("error") val error: String,

    // optional attributes
    @SerialName("error_description") val errorDescription: String? = null,
)
