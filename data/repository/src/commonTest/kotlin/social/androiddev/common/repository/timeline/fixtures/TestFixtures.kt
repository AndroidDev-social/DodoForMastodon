/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
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
