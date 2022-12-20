/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
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
