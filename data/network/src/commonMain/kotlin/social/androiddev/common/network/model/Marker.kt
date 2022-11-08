package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/marker/
 */
@Serializable
data class Marker(
    // required attributes
    @SerialName("home") val home: MarkerHash,
    @SerialName("notifications") val uses: MarkerHash,
)

@Serializable
data class MarkerHash(
    @SerialName("last_read_id") val lastReadId: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("version") val version: Int,
)
