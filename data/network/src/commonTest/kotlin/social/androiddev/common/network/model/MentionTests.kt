package social.androiddev.common.network.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
