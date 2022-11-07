package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class PollTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json = """
        {
            "id": "34830",
            "expires_at": "2019-12-05T04:05:08.302Z",
            "expired": true,
            "multiple": false,
            "votes_count": 10,
            "voters_count": null,
            "voted": true,
            "own_votes":
            [
                1
            ],
            "options":
            [
                {
                    "title": "accept",
                    "votes_count": 6
                },
                {
                    "title": "deny",
                    "votes_count": 4
                }
            ],
            "emojis":
            []
        }
        """.trimIndent()

        // when
        val poll = Json.decodeFromString<Poll>(json)

        // then
        Truth.assertThat(poll.id).isEqualTo("34830")
        Truth.assertThat(poll.expiresAt).isEqualTo("2019-12-05T04:05:08.302Z")
        Truth.assertThat(poll.expired).isEqualTo(true)
        Truth.assertThat(poll.multiple).isEqualTo(false)
        Truth.assertThat(poll.votesCount).isEqualTo(10)
        Truth.assertThat(poll.votersCount).isNull()
        Truth.assertThat(poll.ownVotes).isEqualTo(listOf(1))
        Truth.assertThat(poll.options?.get(0)).isEqualTo(PollHash("accept", 6))
        Truth.assertThat(poll.options?.get(1)).isEqualTo(PollHash("deny", 4))
    }
}
