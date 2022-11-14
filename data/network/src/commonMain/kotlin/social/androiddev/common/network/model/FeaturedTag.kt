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
 * https://docs.joinmastodon.org/entities/featuredtag/
 */
@Serializable
data class FeaturedTag(
    // required attributes
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("statuses_count") val statusesCount: Int,
    @SerialName("last_status_at") val lastStatusAt: String,

    // optional attributes
    @SerialName("history") val history: List<History>? = null,
)
