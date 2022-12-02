/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class PollTests {
    @Test
    fun `deserialize required fields should succeed`() {
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
        assertEquals(expected = "34830", actual = poll.id)
        assertEquals(expected = "2019-12-05T04:05:08.302Z", actual = poll.expiresAt)
        assertEquals(expected = true, actual = poll.expired)
        assertEquals(expected = false, actual = poll.multiple)
        assertEquals(expected = 10, actual = poll.votesCount)
        assertNull(actual = poll.votersCount)
        assertEquals(expected = listOf(1), actual = poll.ownVotes)
        assertEquals(expected = PollHash("accept", 6), actual = poll.options?.get(0))
        assertEquals(expected = PollHash("deny", 4), actual = poll.options?.get(1))
    }
}
