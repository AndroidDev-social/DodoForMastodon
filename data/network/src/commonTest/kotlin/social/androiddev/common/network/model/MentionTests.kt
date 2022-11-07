package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class MentionTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(mentions).isNotNull()
        Truth.assertThat(mentions.size).isEqualTo(2)

        val firstMention = mentions[0]
        Truth.assertThat(firstMention.id).isEqualTo("952529")
        Truth.assertThat(firstMention.username).isEqualTo("alayna")
        Truth.assertThat(firstMention.url).isEqualTo("https://desvox.es/users/alayna")
        Truth.assertThat(firstMention.acct).isEqualTo("alayna@desvox.es")

        val secondMention = mentions[1]
        Truth.assertThat(secondMention.id).isEqualTo("14715")
        Truth.assertThat(secondMention.username).isEqualTo("trwnh")
        Truth.assertThat(secondMention.url).isEqualTo("https://mastodon.social/@trwnh")
        Truth.assertThat(secondMention.acct).isEqualTo("trwnh")

    }
}
