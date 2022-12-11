package social.androiddev.domain.authentication.usecase

import social.androiddev.domain.authentication.model.ApplicationOAuthToken
import social.androiddev.domain.authentication.repository.AuthenticationRepository

class GetSelectedApplicationOAuthToken(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(): ApplicationOAuthToken {

        val server = requireNotNull(authenticationRepository.selectedServer) {
            "Selected Server must not be null"
        }

        val token = requireNotNull(authenticationRepository.getApplicationOAuthToken(server)) {
            "OAuth Token must not be null"
        }

        return token
    }
}