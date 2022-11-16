package social.androiddev.common

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