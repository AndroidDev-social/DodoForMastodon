package social.androiddev.common.repository.timeline.fixtures

import social.androiddev.common.network.MastodonApi
import social.androiddev.common.network.model.Application
import social.androiddev.common.network.model.AvailableInstance
import social.androiddev.common.network.model.Instance
import social.androiddev.common.network.model.NewOauthApplication
import social.androiddev.common.network.model.Privacy
import social.androiddev.common.network.model.Status
import social.androiddev.common.network.model.Token
import social.androiddev.common.persistence.localstorage.DodoAuthStorage


val fakeStorage = object : DodoAuthStorage {
    override var currentDomain: String? = "androiddev.social"

    override suspend fun saveAccessToken(server: String, token: String) {
        TODO("Not yet implemented")
    }

    override fun getAccessToken(server: String): String = "FakeToken"
}

val fakeStatus = Status(
    "",
    "",
    "",
    null,
    "",
    Privacy.direct,
    false,
    "",
)

val fakeApi = object : MastodonApi {
    override suspend fun listInstances(): Result<List<AvailableInstance>> {
        TODO("Not yet implemented")
    }

    override suspend fun createApplication(
        domain: String,
        clientName: String,
        redirectUris: String,
        scopes: String,
        website: String?
    ): Result<NewOauthApplication> {
        TODO("Not yet implemented")
    }

    override suspend fun createAccessToken(
        domain: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        grantType: String,
        code: String,
        scope: String
    ): Result<Token> {
        TODO("Not yet implemented")
    }

    override suspend fun verifyApplication(): Result<Application> {
        TODO("Not yet implemented")
    }

    override suspend fun getInstance(domain: String?): Result<Instance> {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeFeed(
        domain: String,
        accessToken: String
    ): Result<List<Status>> {

        return Result.success(
            listOf<Status>(
                fakeStatus
            )
        )
    }
}