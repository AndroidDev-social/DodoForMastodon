package social.androiddev.domain.authentication.model

data class NewAppOAuthToken(
    val clientId: String,
    val clientSecret: String,
)
