/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MastodonApiTests {
    // TODO: fix loading json from resources
    @Test
    @Ignore
    fun `Instance request should fail with invalid response`() = runTest {
        // given
        // val content: String = javaClass.classLoader.getResource("response_instance_invalid.json").readText()
        val content: String = ""
        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized, content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.getInstance("")

        // then
        assertFalse(actual = result.isSuccess)
        assertNull(actual = result.getOrNull()?.uri)
    }

    // TODO: fix loading json from resources
    @Test
    @Ignore
    fun `Instance request should succeed with required field response`() = runTest {
        // given
        // val content: String = javaClass.classLoader.getResource("response_instance_valid.json").readText()
        val content: String = ""

        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized,
                content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.getInstance("")

        // then
        assertTrue(actual = result.isSuccess)
        assertNotNull(actual = result.getOrNull()?.uri)
    }

    @Test
    @Ignore
    fun `create application request should fail with invalid response`() = runTest {
        val content: String = """
        {
            "id": null,
            "name": "test app",
            "client_id": "bgorLrj8s1CeX_QghuI5NhVsestPXkTyyCBuaSCeYj4",
            "client_secret": "lNmvmMA8_deuGdQOsuZ_dqE7zxQOoociwfTlHB-L1C0",
            "website": null,
            "vapid_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
        """.trimIndent()

        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized,
                content = ByteReadChannel(text = content)
            )
        )

        val result = mastodonApi.createApplication(
            clientName = "",
            redirectUris = "",
            scopes = "",
            website = null
        )

        assertFalse(actual = result.isSuccess)
        assertNull(actual = result.getOrNull())
    }

    @Test
    @Ignore
    fun `create application should succeed with required field response`() = runTest {

        val content: String = """
        {
            "id": "123",
            "name": "test app",
            "client_id": "bgorLrj8s1CeX_QghuI5NhVsestPXkTyyCBuaSCeYj4",
            "client_secret": "lNmvmMA8_deuGdQOsuZ_dqE7zxQOoociwfTlHB-L1C0",
            "website": null,
            "vapid_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
        """.trimIndent()

        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized,
                content = ByteReadChannel(text = content)
            )
        )

        val result = mastodonApi.createApplication(
            clientName = "",
            redirectUris = "",
            scopes = "",
            website = null
        )

        assertTrue(actual = result.isSuccess)
        assertNotNull(actual = result.getOrNull()?.id)
        assertNotNull(actual = result.getOrNull()?.name)
        assertNotNull(actual = result.getOrNull()?.clientId)
        assertNotNull(actual = result.getOrNull()?.clientSecret)
        assertNull(actual = result.getOrNull()?.website)
        assertNull(actual = result.getOrNull()?.vapidKey)
    }

    private fun createMockClient(
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        content: ByteReadChannel
    ): HttpClient {
        return HttpClient(
            MockEngine {
                respond(
                    content = content,
                    status = statusCode,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
        ) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Application.Json
                )
            }
        }
    }
}
