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
package social.androiddev.domain.authentication.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import social.androiddev.domain.authentication.model.AuthStatus
import social.androiddev.domain.authentication.repository.AuthenticationRepository

class GetAuthStatus(private val authenticationRepository: AuthenticationRepository) {
    suspend operator fun invoke(): Flow<AuthStatus> = flow {
        authenticationRepository.getIsAccessTokenPresent().collect { hasAccessToken ->
            if (hasAccessToken == null) {
                emit(AuthStatus.Unknown)
            } else {
                if (hasAccessToken) {
                    emit(AuthStatus.Authorized)
                } else {
                    emit(AuthStatus.Unauthorized)
                }
            }
        }
    }
}
