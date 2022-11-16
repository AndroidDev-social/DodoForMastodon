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