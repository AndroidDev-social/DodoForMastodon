package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class EmojiTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(emoji.shortcode).isEqualTo("blobaww")
        Truth.assertThat(emoji.url).isEqualTo("https://files.mastodon.social/custom_emojis/images/000/011/739/original/blobaww.png")
        Truth.assertThat(emoji.staticUrl).isEqualTo("https://files.mastodon.social/custom_emojis/images/000/011/739/static/blobaww.png")
        Truth.assertThat(emoji.visibleInPicker).isEqualTo(true)
        Truth.assertThat(emoji.category).isEqualTo("Blobs")
    }
}
