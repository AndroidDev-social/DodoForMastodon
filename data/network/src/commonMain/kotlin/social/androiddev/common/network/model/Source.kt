/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/source/
 */
@Serializable
data class Source(
    // base attributes
    @SerialName("note") val note: String,
    @SerialName("fields") val fields: List<Field>,

    // optional attributes
    @SerialName("privacy") val privacy: Privacy,
    @SerialName("sensitive") val sensitive: Boolean,
    @SerialName("language") val language: String,
    @SerialName("follow_requests_count") val followRequestsCount: Int,
)

enum class Privacy { public, unlisted, private, direct }
