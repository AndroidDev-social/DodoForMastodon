package social.androiddev.domain.authentication.usecase

import social.androiddev.domain.authentication.repository.AuthenticationRepository

class CreateAccessToken(
    private val authenticationRepository: AuthenticationRepository
) {

    suspend operator fun invoke(authCode: String, server: String): Boolean {
        val token = authenticationRepository.createAccessToken(
            server = server,
            authCode = authCode,
            scope = "read write follow push"
        )

        return if (token != null) {
            authenticationRepository.saveAccessToken(server = server, token = token)
            true
        } else {
            false
        }
    }
}