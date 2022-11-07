package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class AnnouncementTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(announcement.id).isEqualTo("8")
        Truth.assertThat(announcement.text).contains("Looks like there was an issue processing audio attachments without embedded art since yesterday due to an experimental new feature")
        Truth.assertThat(announcement.published).isEqualTo(true)
        Truth.assertThat(announcement.allDay).isEqualTo(false)
        Truth.assertThat(announcement.createdAt).isEqualTo("2020-07-03T01:27:38.726Z")
        Truth.assertThat(announcement.updatedAt).isEqualTo("2020-07-03T01:27:38.752Z")
        Truth.assertThat(announcement.read).isEqualTo(true)
        Truth.assertThat(announcement.reactions).isNotNull()
        Truth.assertThat(announcement.reactions.size).isEqualTo(4)
    }
}
