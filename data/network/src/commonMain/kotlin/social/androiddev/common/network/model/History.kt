package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/history/
 */
@Serializable
data class History(
    // required attributes
    @SerialName("day") val day: String,
    @SerialName("uses") val uses: String,
    @SerialName("accounts") val accounts: String,
)
