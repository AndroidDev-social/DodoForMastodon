package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

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
