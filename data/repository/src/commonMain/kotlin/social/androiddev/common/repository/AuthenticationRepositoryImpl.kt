/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.repository

import social.androiddev.common.network.MastodonApi
import social.androiddev.domain.authentication.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    private val mastodonApi: MastodonApi
) : AuthenticationRepository {

    override suspend fun createApplicationClient(
        clientName: String,
        redirectUris: String,
        scopes: String,
        website: String?
    ): Boolean {
        val application = mastodonApi.createApplication(
            clientName = clientName,
            redirectUris = redirectUris,
            scopes = scopes,
            website = website
        ).getOrNull()

        return if (application == null) {
            false
        } else {
            // TODO store in cache for later use
            val clientId = application.clientId
            val clientSecret = application.clientSecret
            true
        }
    }
}
