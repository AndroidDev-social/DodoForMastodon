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
import social.androiddev.common.network.fixtures.homeFeed
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class MastodonApiTests {

    @Test
    fun `instance list should be parsed correctly`() = runTest {
        // given
        val content = """
        [
            {
                "domain": "androiddev.social",
                "version": "4.0.2",
                "description": "Public Mastodon server for Android Community.",
                "languages":
                [
                    "en"
                ],
                "region": "",
                "categories":
                [
                    "general"
                ],
                "proxied_thumbnail": "https://proxy.joinmastodon.org/86c214c46591d5e5bc7d14cda56fb5193383c524/68747470733a2f2f73332e65752d63656e7472616c2d322e7761736162697379732e636f6d2f6d6173746f646f6e776f726c642f736974655f75706c6f6164732f66696c65732f3030302f3030302f3030342f4031782f316365303634373033633836626231632e706e67",
                "total_users": 1800,
                "last_week_users": 1700,
                "approval_required": true,
                "language": "en",
                "category": "general"
            },
            {
                "domain": "mstdn.social",
                "version": "4.0.2",
                "description": "Explore and discover the fediverse together with your online family on mstdn!",
                "languages":
                [
                    "en"
                ],
                "region": "",
                "categories":
                [
                    "general"
                ],
                "proxied_thumbnail": "https://proxy.joinmastodon.org/0044bc3b6ca4a01574250244c81cd30950f0c67f/68747470733a2f2f6d656469612e6d7374646e2e736f6369616c2f736974655f75706c6f6164732f66696c65732f3030302f3030302f3030342f4031782f633131613264616239626235336162632e706e67",
                "total_users": 150652,
                "last_week_users": 38432,
                "approval_required": false,
                "language": "en",
                "category": "general"
            }
        ]
        """.trimIndent()
        val mastodonApi = MastodonApiKtor(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.UnprocessableEntity, content = ByteReadChannel(text = content)
            )
        )

        // val when
        val result = mastodonApi.listInstances()

        // then
        assertTrue(actual = result.isSuccess)
        assertNotNull(actual = result.getOrNull())
        assertEquals(actual = result.getOrNull()?.get(0)?.domain, expected = "androiddev.social")
    }

    /**
     * @see https://docs.joinmastodon.org/methods/apps/#200-ok
     */
    @Test
    fun `create a new application with valid response should succeed`() = runTest {
        // given
        val content: String = validApplicationCreation

        val mastodonApi: MastodonApi = MastodonApiKtor(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized,
                content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.createApplication(
            domain = "androiddev.social",
            clientName = "Dodo",
            redirectUris = "https://androiddev.social/oauth",
            scopes = "read",
            website = "https://androiddev.social"
        )

        // then
        assertTrue(actual = result.isSuccess)
        assertNotNull(actual = result.getOrNull())
        assertEquals(actual = result.getOrNull()?.id, expected = "563419")
        assertEquals(actual = result.getOrNull()?.name, expected = "test app")
        assertEquals(actual = result.getOrNull()?.vapidKey, expected = "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M=")

        assertEquals(expected = "TWhM-tNSuncnqN7DBJmoyeLnk6K3iJJ71KKXxgL1hPM", actual = result.getOrNull()?.clientId)
        assertEquals(expected = "ZEaFUFmF0umgBX1qKJDjaU99Q31lDkOU8NutzTOoliw", actual = result.getOrNull()?.clientSecret)
        assertEquals(expected = "https://androiddev.social", actual = result.getOrNull()?.website)
    }

    /**
     * @see https://docs.joinmastodon.org/methods/apps/#422-unprocessable-entity
     */
    @Test
    fun `create a new application with missing parameter should fail`() = runTest {
        // given
        val content = """"error": "Validation failed: Redirect URI must be an absolute URI.""""
        val mastodonApi = MastodonApiKtor(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.UnprocessableEntity, content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.createApplication(
            domain = "androiddev.social",
            clientName = "Dodo",
            redirectUris = "https://androiddev.social/oauth",
            scopes = "read",
            website = "https://androiddev.social"
        )

        // then
        assertTrue(actual = result.isFailure)
        assertNull(actual = result.getOrNull())
    }

    /**
     * @see https://docs.joinmastodon.org/methods/apps/#200-ok-1
     */
    @Test
    fun `verify oauth credentials for new application should succeed`() = runTest {
        // given
        val content: String = applicationVerificationValid
        val mastodonApi = MastodonApiKtor(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.OK, content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.verifyApplication()

        // then
        assertTrue(actual = result.isSuccess)
    }

    /**
     * @see https://docs.joinmastodon.org/methods/apps/#401-unauthorized
     */
    @Test
    fun `verify oauth credentials for new application with invalid token should fail`() = runTest {
        // given
        val content: String = applicationVerificationFailed
        val mastodonApi = MastodonApiKtor(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized, content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.verifyApplication()

        // then
        assertTrue(actual = result.isFailure)
    }

    /**
     * @see https://docs.joinmastodon.org/methods/instance/#200-ok
     */
    @Test
    fun `view server information should succeed`() = runTest {
        val content: String = serverInformationValid
        val mastodonApi = MastodonApiKtor(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.OK, content = ByteReadChannel(text = content)
            )
        )

        // when
        val result = mastodonApi.getInstance("androiddev.social")

        // then
        assertTrue(actual = result.isSuccess)
    }
    // TODO MIKE:  Harden tests to validate inputs
    @Test
    fun `view home feed should succeed`() = runTest {
        val mastodonApi = MastodonApiKtor(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.OK, content = ByteReadChannel(text = homeFeed)
            )
        )

        // when
        val result = mastodonApi.getHomeFeed("", "")

        // then
        assertTrue(actual = result.isSuccess)
        assertTrue { result.getOrThrow().isNotEmpty() }
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

    private val validInstanceResponse = """
        {
          "uri": "mastodon.social",
          "title": "Mastodon",
          "description": "Server run by the main developers of the project <img draggable=\"false\" alt=\"🐘\" class=\"emojione\" src=\"https://mastodon.social/emoji/1f418.svg\" /> It is not focused on any particular niche interest - everyone is welcome as long as you follow our code of conduct!",
          "short_description": "Server run by the main developers of the project <img draggable=\"false\" alt=\"🐘\" class=\"emojione\" src=\"https://mastodon.social/emoji/1f418.svg\" /> It is not focused on any particular niche interest - everyone is welcome as long as you follow our code of conduct!",
          "email": "staff@mastodon.social",
          "version": "3.0.1",
          "languages":
          [
            "en"
          ],
          "registrations": true,
          "approval_required": false,
          "invites_enabled": true,
          "urls":
          {
            "streaming_api": "wss://mastodon.social"
          },
          "stats":
          {
            "user_count": 415526,
            "status_count": 17085754,
            "domain_count": 11834
          },
          "user_count": 415526,
          "thumbnail": "https://files.mastodon.social/site_uploads/files/000/000/001/original/vlcsnap-2018-08-27-16h43m11s127.png",
          "contact_account":
          {
            "id": "1",
            "username": "Gargron",
            "acct": "Gargron",
            "display_name": "Eugen",
            "locked": false,
            "bot": false,
            "created_at": "2016-03-16T14:34:26.392Z",
            "note": "<p>Developer of Mastodon and administrator of mastodon.social. I post service announcements, development updates, and personal stuff.</p>",
            "url": "https://mastodon.social/@Gargron",
            "avatar": "https://files.mastodon.social/accounts/avatars/000/000/001/original/d96d39a0abb45b92.jpg",
            "avatar_static": "https://files.mastodon.social/accounts/avatars/000/000/001/original/d96d39a0abb45b92.jpg",
            "header": "https://files.mastodon.social/accounts/headers/000/000/001/original/c91b871f294ea63e.png",
            "header_static": "https://files.mastodon.social/accounts/headers/000/000/001/original/c91b871f294ea63e.png",
            "followers_count": 317112,
            "following_count": 453,
            "statuses_count": 60903,
            "last_status_at": "2019-11-26T21:14:44.522Z",
            "emojis": [],
            "discoverable": false,
            "fields":
            [
              {
                "name": "Patreon",
                "value": "<a href=\"https://www.patreon.com/mastodon\" rel=\"me nofollow noopener noreferrer\" target=\"_blank\"><span class=\"invisible\">https://www.</span><span class=\"\">patreon.com/mastodon</span><span class=\"invisible\"></span}",
                "verified_at": null
              },
              {
                "name": "Homepage",
                "value": "<a href=\"https://zeonfederated.com\" rel=\"me nofollow noopener noreferrer\" target=\"_blank\"><span class=\"invisible\">https://</span><span class=\"\">zeonfederated.com</span><span class=\"invisible\"></span}",
                "verified_at": "2019-07-15T18:29:57.191+00:00"
              }
            ]
          }
        }
    """.trimIndent()

    private val validApplicationCreation = """
        {
          "id": "563419",
          "name": "test app",
          "website": "https://androiddev.social",
          "redirect_uri": "urn:ietf:wg:oauth:2.0:oob",
          "client_id": "TWhM-tNSuncnqN7DBJmoyeLnk6K3iJJ71KKXxgL1hPM",
          "client_secret": "ZEaFUFmF0umgBX1qKJDjaU99Q31lDkOU8NutzTOoliw",
          "vapid_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
    """.trimIndent()

    private val applicationVerificationValid = """
        {
          "id": "563419",
          "name": "test app",
          "website": null,
          "vapid_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
    """.trimIndent()

    private val applicationVerificationFailed = """
        {"error": "The access token is invalid"}
    """.trimIndent()

    private val serverInformationValid = """
        {
          "uri": "mastodon.social",
          "title": "Mastodon",
          "description": "Server run by the main developers of the project <img draggable=\"false\" alt=\"🐘\" class=\"emojione\" src=\"https://mastodon.social/emoji/1f418.svg\" /> It is not focused on any particular niche interest - everyone is welcome as long as you follow our code of conduct!",
          "short_description": "Server run by the main developers of the project <img draggable=\"false\" alt=\"🐘\" class=\"emojione\" src=\"https://mastodon.social/emoji/1f418.svg\" /> It is not focused on any particular niche interest - everyone is welcome as long as you follow our code of conduct!",
          "email": "staff@mastodon.social",
          "version": "3.0.1",
          "languages":
          [
            "en"
          ],
          "registrations": true,
          "approval_required": false,
          "invites_enabled": true,
          "urls":
          {
            "streaming_api": "wss://mastodon.social"
          },
          "stats":
          {
            "user_count": 415526,
            "status_count": 17085754,
            "domain_count": 11834
          },
          "user_count": 415526,
        
          "thumbnail": "https://files.mastodon.social/site_uploads/files/000/000/001/original/vlcsnap-2018-08-27-16h43m11s127.png",
          "contact_account":
          {
            "id": "1",
            "username": "Gargron",
            "acct": "Gargron",
            "display_name": "Eugen",
            "locked": false,
            "bot": false,
            "created_at": "2016-03-16T14:34:26.392Z",
            "note": "<p>Developer of Mastodon and administrator of mastodon.social. I post service announcements, development updates, and personal stuff.</p>",
            "url": "https://mastodon.social/@Gargron",
            "avatar": "https://files.mastodon.social/accounts/avatars/000/000/001/original/d96d39a0abb45b92.jpg",
            "avatar_static": "https://files.mastodon.social/accounts/avatars/000/000/001/original/d96d39a0abb45b92.jpg",
            "header": "https://files.mastodon.social/accounts/headers/000/000/001/original/c91b871f294ea63e.png",
            "header_static": "https://files.mastodon.social/accounts/headers/000/000/001/original/c91b871f294ea63e.png",
            "followers_count": 317112,
            "following_count": 453,
            "statuses_count": 60903,
            "last_status_at": "2019-11-26T21:14:44.522Z",
            "emojis": [],
            "discoverable": false,
            "fields":
            [
              {
                "name": "Patreon",
                "value": "<a href=\"https://www.patreon.com/mastodon\" rel=\"me nofollow noopener noreferrer\" target=\"_blank\"><span class=\"invisible\">https://www.</span><span class=\"\">patreon.com/mastodon</span><span class=\"invisible\"></span}",
                "verified_at": null
              },
              {
                "name": "Homepage",
                "value": "<a href=\"https://zeonfederated.com\" rel=\"me nofollow noopener noreferrer\" target=\"_blank\"><span class=\"invisible\">https://</span><span class=\"\">zeonfederated.com</span><span class=\"invisible\"></span}",
                "verified_at": "2019-07-15T18:29:57.191+00:00"
              }
            ]
          }
        }
    """.trimIndent()
}
