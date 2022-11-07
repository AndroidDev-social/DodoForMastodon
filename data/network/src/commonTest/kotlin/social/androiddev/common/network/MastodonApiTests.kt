package social.androiddev.common.network

import com.google.common.truth.Truth
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
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class MastodonApiTests {
    @Before
    fun setup() {
        // setup mocks
    }

    @Test
    fun `Instance request should fail with invalid response`() = runBlocking {
        // given
        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized, content = ByteReadChannel(
                    """
                    {"error":"Access denied. Please check your credentials."}
                """.trimIndent()
                )
            )
        )

        // when
        val result = mastodonApi.getInstance("")

        // then
        Truth.assertThat(result.isSuccess).isEqualTo(false)
        Truth.assertThat(result.getOrNull()?.uri).isNull()
    }

    @Test
    fun `Instance request should succeed with required field response`() = runBlocking {
        // given
        val mastodonApi = MastodonApiImpl(
            httpClient = createMockClient(
                statusCode = HttpStatusCode.Unauthorized, content = ByteReadChannel(
                    """
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
                )
            )
        )

        // when
        val result = mastodonApi.getInstance("")

        // then
        Truth.assertThat(result.isSuccess).isEqualTo(true)
        Truth.assertThat(result.getOrNull()?.uri).isNotNull()
    }


    private fun createMockClient(
        statusCode: HttpStatusCode = HttpStatusCode.OK, content: ByteReadChannel
    ): HttpClient {
        return HttpClient(MockEngine {
            respond(
                content = content, status = statusCode, headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }, contentType = ContentType.Application.Json
                )
            }
        }
    }
}
