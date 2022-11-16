package social.androiddev.domain.authentication.repository

interface AuthenticationRepository {

    suspend fun createApplicationClient(
        clientName: String,
        redirectUris: String,
        scopes: String,
        website: String?
    ): Boolean
}