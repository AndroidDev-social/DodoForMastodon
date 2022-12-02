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
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class AnnouncementTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "id": "8",
            "text": "<p>Looks like there was an issue processing audio attachments without embedded art since yesterday due to an experimental new feature. That issue has now been fixed, so you may see older posts with audio from other servers pop up in your feeds now as they are being finally properly processed. Sorry!</p>",
            "published": true,
            "all_day": false,
            "created_at": "2020-07-03T01:27:38.726Z",
            "updated_at": "2020-07-03T01:27:38.752Z",
            "read": true,
            "reactions":
            [
                {
                    "name": "bongoCat",
                    "count": 9,
                    "me": false,
                    "url": "https://files.mastodon.social/custom_emojis/images/000/067/715/original/fdba57dff7576d53.png",
                    "static_url": "https://files.mastodon.social/custom_emojis/images/000/067/715/static/fdba57dff7576d53.png"
                },
                {
                    "name": "thonking",
                    "count": 1,
                    "me": false,
                    "url": "https://files.mastodon.social/custom_emojis/images/000/098/690/original/a8d36edc4a7032e8.png",
                    "static_url": "https://files.mastodon.social/custom_emojis/images/000/098/690/static/a8d36edc4a7032e8.png"
                },
                {
                    "name": "AAAAAA",
                    "count": 1,
                    "me": false,
                    "url": "https://files.mastodon.social/custom_emojis/images/000/071/387/original/AAAAAA.png",
                    "static_url": "https://files.mastodon.social/custom_emojis/images/000/071/387/static/AAAAAA.png"
                },
                {
                    "name": "🤔",
                    "count": 1,
                    "me": true
                }
            ]
        }
        """.trimIndent()

        // when
        val announcement = Json.decodeFromString<Announcement>(json)

        // then
        assertEquals(expected = "8", actual = announcement.id)
        assertContains(
            announcement.text,
            "Looks like there was an issue processing audio attachments without embedded art since yesterday due to an experimental new feature",
        )
        assertEquals(expected = true, actual = announcement.published)
        assertEquals(expected = false, actual = announcement.allDay)
        assertEquals(expected = "2020-07-03T01:27:38.726Z", actual = announcement.createdAt)
        assertEquals(expected = "2020-07-03T01:27:38.752Z", actual = announcement.updatedAt)
        assertEquals(expected = true, actual = announcement.read)
        assertNotNull(actual = announcement.reactions)
        assertEquals(expected = 4, actual = announcement.reactions.size)
    }
}
