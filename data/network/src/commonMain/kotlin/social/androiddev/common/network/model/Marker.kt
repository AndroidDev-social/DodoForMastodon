/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
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
