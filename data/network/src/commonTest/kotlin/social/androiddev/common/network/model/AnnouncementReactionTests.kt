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

class AnnouncementReactionTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        [
            {
                "name": "bongoCat",
                "count": 9,
                "me": false,
                "url": "https://files.mastodon.social/custom_emojis/images/000/067/715/original/fdba57dff7576d53.png",
                "static_url": "https://files.mastodon.social/custom_emojis/images/000/067/715/static/fdba57dff7576d53.png"
            },
            {
                "name": "🤔",
                "count": 1,
                "me": true
            }
        ]
        """.trimIndent()

        // when
        val reactions = Json.decodeFromString<List<AnnouncementReaction>>(json)

        // then
        assertNotNull(actual = reactions)
        assertEquals(expected = 2, actual = reactions.size)
        assertNotNull(actual = reactions[0])
        assertNotNull(actual = reactions[1])

        val firstReaction = reactions[0]
        assertEquals(expected = "bongoCat", actual = firstReaction.name)
        assertEquals(expected = 9, actual = firstReaction.count)
        assertEquals(expected = false, actual = firstReaction.me)
        assertEquals(expected = "https://files.mastodon.social/custom_emojis/images/000/067/715/original/fdba57dff7576d53.png", actual = firstReaction.url)
        assertEquals(expected = "https://files.mastodon.social/custom_emojis/images/000/067/715/static/fdba57dff7576d53.png", actual = firstReaction.staticUrl)

        val secondReaction = reactions[1]
        assertEquals(expected = "🤔", actual = secondReaction.name)
        assertEquals(expected = 1, actual = secondReaction.count)
        assertEquals(expected = true, actual = secondReaction.me)
    }
}
