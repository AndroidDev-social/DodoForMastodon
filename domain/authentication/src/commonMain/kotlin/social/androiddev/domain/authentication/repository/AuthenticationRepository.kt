/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.domain.authentication.repository

import social.androiddev.domain.authentication.model.ApplicationOAuthToken
import social.androiddev.domain.authentication.model.NewAppOAuthToken

interface AuthenticationRepository {

    suspend fun createApplicationClient(
        domain: String,
        clientName: String,
        redirectUris: String,
        scopes: String,
        website: String?
    ): NewAppOAuthToken?

    suspend fun saveApplication(
        token: NewAppOAuthToken,
        domain: String,
    )

    suspend fun createAccessToken(
        authCode: String,
        server: String,
        scope: String,
        grantType: String,
    ): String?
    fun saveAccessToken(server: String, token: String)

    val selectedServer: String?

    suspend fun getApplicationOAuthToken(server: String): ApplicationOAuthToken?
}
