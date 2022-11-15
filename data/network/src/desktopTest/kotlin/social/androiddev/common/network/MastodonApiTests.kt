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

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.*

/**
 * I was unable to read a .json file from commonTest, and so API tests must be done from
 * desktopTest, androidTest, and/or iosTest.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MastodonApiTests {

    @Test
    fun `Instance request should fail with invalid response`() = runTest {
        // given

        // given
        val content = MR.files.response_account_required.readText()
        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized,
                content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.getInstance("")

        // then
        assertFalse(actual = result.isSuccess)
        assertNull(actual = result.getOrNull()?.uri)
    }

    @Test
    fun `Instance request should succeed with required field response`() = runTest {
        // given
        val content = MR.files.response_instance_valid.readText()
        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized, content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.getInstance("")

        // then
        assertTrue(actual = result.isSuccess)
        assertNotNull(actual = result.getOrNull()?.uri)

    }

    private fun createMockClient(
        statusCode: HttpStatusCode = HttpStatusCode.OK,
        content: ByteReadChannel
    ): HttpClient {
        return HttpClient(
            MockEngine {
                respond(
                    content = content, status = statusCode, headers = headersOf(HttpHeaders.ContentType, "application/json")
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
