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
package social.androiddev.common.persistence.localstorage

import kotlinx.coroutines.flow.Flow

/**
 * Contract for key => value storage for any authentication related data
 */
interface DodoAuthStorage {

    /**
     * The current domain/server the user is logged into.
     * This is used to query the right account info since
     * the user can have multiple accounts
     */
    var currentDomain: String?

    /**
     * List of servers that user has access to
     */
    val authorizedServersFlow: Flow<List<String>>

    /**
     * Save the @param token keyed by @param server
     */
    suspend fun saveAccessToken(server: String, token: String)

    /**
     * Get the Access token for @param server
     */
    fun getAccessToken(server: String): String?
}
