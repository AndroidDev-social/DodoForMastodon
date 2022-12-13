/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.domain.authentication.usecase

import social.androiddev.domain.authentication.repository.AuthenticationRepository

class AuthenticateClient(
    private val authenticationRepository: AuthenticationRepository,
) {

    suspend operator fun invoke(
        domain: String,
        clientName: String,
        redirectURIs: String,
        scopes: String,
        website: String? = null,
    ): Boolean {
        val oAuthToken = authenticationRepository.createApplicationClient(
            domain = domain,
            clientName = clientName,
            redirectUris = redirectURIs,
            scopes = scopes,
            website = website,
        )

        return if (oAuthToken != null) {
            authenticationRepository.saveApplication(
                token = oAuthToken,
                domain = domain,
            )
            true
        } else {
            false
        }
    }
}
