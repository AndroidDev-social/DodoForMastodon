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

class EmojiTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "shortcode": "blobaww",
            "url": "https://files.mastodon.social/custom_emojis/images/000/011/739/original/blobaww.png",
            "static_url": "https://files.mastodon.social/custom_emojis/images/000/011/739/static/blobaww.png",
            "visible_in_picker": true,
            "category": "Blobs"
        }
        """.trimIndent()

        // when
        val emoji = Json.decodeFromString<Emoji>(json)

        // then
        assertEquals(expected = "blobaww", actual = emoji.shortcode)
        assertEquals(expected = "https://files.mastodon.social/custom_emojis/images/000/011/739/original/blobaww.png", actual = emoji.url)
        assertEquals(expected = "https://files.mastodon.social/custom_emojis/images/000/011/739/static/blobaww.png", actual = emoji.staticUrl)
        assertEquals(expected = true, actual = emoji.visibleInPicker)
        assertEquals(expected = "Blobs", actual = emoji.category)
    }
}
