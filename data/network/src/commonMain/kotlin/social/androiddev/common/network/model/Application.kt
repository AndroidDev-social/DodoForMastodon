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
 * https://docs.joinmastodon.org/entities/application/
 */
@Serializable
data class Application(
    val id: String?=null,
    val name: String,
    @SerialName("vapid_key") val vapidKey: String?=null,

    // optional attributes
    val website: String? = null,
)

@Serializable
data class NewOauthApplication(
    val id: String,
    val name: String,
    @SerialName("vapid_key") val vapidKey: String,

    // client attributes
    @SerialName("client_id") val clientId: String,
    @SerialName("client_secret") val clientSecret: String,

    // optional attributes
    val website: String? = null,
)
