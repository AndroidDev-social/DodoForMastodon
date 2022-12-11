package social.androiddev.domain.authentication.model

data class ApplicationOAuthToken(
    val server: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String,
)
