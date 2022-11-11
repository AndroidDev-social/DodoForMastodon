package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

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
