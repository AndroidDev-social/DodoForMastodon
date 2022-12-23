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

@Serializable
data class AvailableInstance(
    val domain: String,
    val version: String,
    val description: String,
    val languages: List<String>,
    val region: String,
    val categories: List<String>,
    @SerialName("proxied_thumbnail") val thumbnail: String,
    @SerialName("total_users") val totalUsers: Int,
    @SerialName("last_week_users") val lastWeekUsers: Int,
    @SerialName("approval_required") val approvalRequired: Boolean,
    val language: String,
    val category: String,
)
