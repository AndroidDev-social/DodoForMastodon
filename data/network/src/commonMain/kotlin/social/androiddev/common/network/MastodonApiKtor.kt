/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import social.androiddev.common.network.model.Application
import social.androiddev.common.network.model.AvailableInstance
import social.androiddev.common.network.model.Instance
import social.androiddev.common.network.model.NewOauthApplication
import social.androiddev.common.network.model.Token
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
        return runCatchingIgnoreCancelled {
            httpClient.post {
                url {
                    protocol = URLProtocol.HTTPS
                    host = domain
                    path("/oauth/token")
                }
                formData {
                    append("client_id", clientId)
                    append("client_secret", clientSecret)
                    append("redirect_uri", redirectUri)
                    append("grant_type", grantType)
                    append("code", code)
                    append("scope", scope)
                }
            }.body()
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
//            val s = httpClient.submitForm(
//                formParameters = Parameters.build {
//                    append("client_name", clientName)
//                    append("redirect_uris", redirectUris)
//                    append("scopes", scopes)
//                    if (website != null) {
//                        append("website", website)
//                    }
//                },
//            ) {
//                url {
//                    protocol = URLProtocol.HTTPS
//                    host = domain
//                    path("/api/v1/apps")
//                }
//            }
//            println("OMID~~~: ${s.bodyAsText()}")
//            s.body()

            httpClient.post {
                url {
                    host = domain
                    path("/api/v1/apps")
                }
                contentType(ContentType.Application.Json)
//                header(HttpHeaders.ContentType, ContentType.Application.Json)
                setBody(
                    CreateApplicationBody(
                        scopes = scopes,
                        clientName = clientName,
                        redirectUris = redirectUris
                    )
                )
//                formData {
//                    append("client_name", clientName)
//                    append("redirect_uris", redirectUris)
//                    append("scopes", scopes)
//                    if (website != null) {
//                        append("website", website)
//                    }
//                }
            }.body()
        }.onFailure { t ->
            t.printStackTrace()
        }
    }

    @Serializable
    private data class CreateApplicationBody(
        val scopes: String,
        @SerialName("client_name") val clientName: String,
        @SerialName("redirect_uris") val redirectUris: String,
    )

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
}
