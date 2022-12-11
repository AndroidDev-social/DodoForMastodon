/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.repository

import social.androiddev.common.network.MastodonApi
import social.androiddev.common.persistence.AuthenticationDatabase
import social.androiddev.common.persistence.localstorage.DodoKeyValueStorage
import social.androiddev.domain.authentication.model.NewAppOAuthToken
import social.androiddev.domain.authentication.repository.AuthenticationRepository

internal class AuthenticationRepositoryImpl(
    private val mastodonApi: MastodonApi,
    private val database: AuthenticationDatabase,
    private val keyValueStorage: DodoKeyValueStorage,
) : AuthenticationRepository {

    override suspend fun saveApplication(token: NewAppOAuthToken, domain: String) {
        database.applicationQueries.insertApplication(
            instance = domain,
            client_id = token.clientId,
            client_secret = token.clientSecret,
        )
        keyValueStorage.currentDomain = domain
    }

    override suspend fun createApplicationClient(
        domain: String,
        clientName: String,
        redirectUris: String,
        scopes: String,
        website: String?
    ): NewAppOAuthToken? {
        val application = mastodonApi.createApplication(
            domain = domain,
            clientName = clientName,
            redirectUris = redirectUris,
            scopes = scopes,
            website = website
        ).getOrNull()

        return application?.let { application ->
            NewAppOAuthToken(
                clientId = application.clientId,
                clientSecret = application.clientSecret,
            )
        }
    }
}
