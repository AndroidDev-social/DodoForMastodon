/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.domain.authentication.usecase

import social.androiddev.domain.authentication.repository.AuthenticationRepository

class AuthenticateClient(
    private val authenticationRepository: AuthenticationRepository,
) {

    suspend operator fun invoke(
        clientName: String,
        redirectURIs: String,
        scopes: String = "read write follow push",
        website: String? = null,
    ): Boolean = authenticationRepository.createApplicationClient(
        clientName = clientName,
        redirectUris = redirectURIs,
        scopes = scopes,
        website = website
    )
}
