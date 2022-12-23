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
package social.androiddev.common.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.SerializationException
import social.androiddev.common.logging.DodoLogger
import social.androiddev.common.network.model.Application
import social.androiddev.common.network.model.AvailableInstance
import social.androiddev.common.network.model.Instance
import social.androiddev.common.network.model.NewOauthApplication
import social.androiddev.common.network.model.Status
import social.androiddev.common.network.model.Token
import social.androiddev.common.network.model.request.CreateAccessTokenBody
import social.androiddev.common.network.model.request.CreateApplicationBody
import social.androiddev.common.network.util.runCatchingIgnoreCancelled

internal class MastodonApiKtor(
    private val httpClient: HttpClient,
) : MastodonApi {

    override suspend fun listInstances(): Result<List<AvailableInstance>> {
        return runCatchingIgnoreCancelled {
            httpClient.get("https://api.joinmastodon.org/servers").body()
        }
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
        return runCatchingIgnoreCancelled<Token> {
            httpClient.post {
                url {
                    host = domain
                    path("/oauth/token")
                }
                contentType(ContentType.Application.Json)
                setBody(
                    CreateAccessTokenBody(
                        scope = scope,
                        code = code,
                        clientId = clientId,
                        clientSecret = clientSecret,
                        redirectUri = redirectUri,
                        grantType = grantType,
                    )
                )
            }.body()
        }.onFailure { t ->
            DodoLogger.w {
                "Error when creating access token. e=${t.message}"
            }
        }
    }

    override suspend fun createApplication(
        domain: String,
        clientName: String,
        redirectUris: String,
        scopes: String,
        website: String?
    ): Result<NewOauthApplication> {
        return runCatchingIgnoreCancelled<NewOauthApplication> {
            httpClient.post {
                url {
                    host = domain
                    path("/api/v1/apps")
                }
                contentType(ContentType.Application.Json)
                setBody(
                    CreateApplicationBody(
                        scopes = scopes,
                        clientName = clientName,
                        redirectUris = redirectUris
                    )
                )
            }.body()
        }.onFailure { t ->
            DodoLogger.w {
                "Error when creating application. e=${t.message}"
            }
        }
    }

    override suspend fun verifyApplication(): Result<Application> {
        return runCatchingIgnoreCancelled<Application> {
            httpClient.get("/api/v1/apps/verify_credentials") {
                headers {
                    append(HttpHeaders.Authorization, "testAuthorization")
                }
            }.body()
        }
    }

    override suspend fun getInstance(domain: String?): Result<Instance> {
        return try {
            Result.success(
                httpClient.get("/api/v1/instance") {
                    domain?.let {
                        headers.append("domain", domain)
                    }
                }.body()
            )
        } catch (exception: SerializationException) {
            Result.failure(exception = exception)
        } catch (exception: ResponseException) {
            Result.failure(exception = exception)
        }
    }

    override suspend fun getHomeFeed(domain: String, accessToken: String): Result<List<Status>> {
        return runCatchingIgnoreCancelled {
            httpClient.get {
                url {
                    host = domain
                    path("/api/v1/timelines/home")
                }
                headers {
                    append(HttpHeaders.Authorization, "Bearer $accessToken")
                }
            }.body()
        }
    }
}
