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
import kotlin.test.assertNotNull

class MentionTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        [
            {
                "id": "952529",
                "username": "alayna",
                "url": "https://desvox.es/users/alayna",
                "acct": "alayna@desvox.es"
            },
            {
                "id": "14715",
                "username": "trwnh",
                "url": "https://mastodon.social/@trwnh",
                "acct": "trwnh"
            }
        ]
        """.trimIndent()

        // when
        val mentions = Json.decodeFromString<List<Mention>>(json)

        // then
        assertNotNull(actual = mentions)
        assertEquals(expected = 2, actual = mentions.size)

        val firstMention = mentions[0]
        assertEquals(expected = "952529", actual = firstMention.id)
        assertEquals(expected = "alayna", actual = firstMention.username)
        assertEquals(expected = "https://desvox.es/users/alayna", actual = firstMention.url)
        assertEquals(expected = "alayna@desvox.es", actual = firstMention.acct)

        val secondMention = mentions[1]
        assertEquals(expected = "14715", actual = secondMention.id)
        assertEquals(expected = "trwnh", actual = secondMention.username)
        assertEquals(expected = "https://mastodon.social/@trwnh", actual = secondMention.url)
        assertEquals(expected = "trwnh", actual = secondMention.acct)
    }
}
