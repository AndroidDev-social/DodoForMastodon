package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class AnnouncementReactionTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(reactions).isNotNull()
        Truth.assertThat(reactions.size).isEqualTo(2)
        Truth.assertThat(reactions[0]).isNotNull()
        Truth.assertThat(reactions[1]).isNotNull()

        val firstReaction = reactions[0]
        Truth.assertThat(firstReaction.name).isEqualTo("bongoCat")
        Truth.assertThat(firstReaction.count).isEqualTo(9)
        Truth.assertThat(firstReaction.me).isEqualTo(false)
        Truth.assertThat(firstReaction.url).isEqualTo("https://files.mastodon.social/custom_emojis/images/000/067/715/original/fdba57dff7576d53.png")
        Truth.assertThat(firstReaction.staticUrl).isEqualTo("https://files.mastodon.social/custom_emojis/images/000/067/715/static/fdba57dff7576d53.png")

        val secondReaction = reactions[1]
        Truth.assertThat(secondReaction.name).isEqualTo("🤔")
        Truth.assertThat(secondReaction.count).isEqualTo(1)
        Truth.assertThat(secondReaction.me).isEqualTo(true)
    }
}
