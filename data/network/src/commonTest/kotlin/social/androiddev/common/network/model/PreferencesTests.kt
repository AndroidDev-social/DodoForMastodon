package social.androiddev.common.network.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class PreferencesTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "posting:default:visibility": "public",
            "posting:default:sensitive": false,
            "posting:default:language": null,
            "reading:expand:media": "default",
            "reading:expand:spoilers": false
        }
        """.trimIndent()

        // when
        val preferences = Json.decodeFromString<Preferences>(json)

        // then
        assertEquals(expected = PostingVisibility.public, actual = preferences.postingVisibility)
        assertEquals(expected = false, actual = preferences.postingSensitive)
        assertNull(actual = preferences.postingLanguage)
        assertEquals(expected = ReadingMedia.default, actual = preferences.readingMedia)
        assertEquals(expected = false, actual = preferences.readingSpoilers)
    }
}
