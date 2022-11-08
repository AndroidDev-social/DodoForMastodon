package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/context/
 */
@Serializable
data class Context(
    // required attributes
    @SerialName("ancestors") val ancestors: List<Status>,
    @SerialName("descendants") val descendants: List<Status>,
)
