package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class PreferencesTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(preferences.postingVisibility).isEqualTo(PostingVisibility.public)
        Truth.assertThat(preferences.postingSensitive).isEqualTo(false)
        Truth.assertThat(preferences.postingLanguage).isNull()
        Truth.assertThat(preferences.readingMedia).isEqualTo(ReadingMedia.default)
        Truth.assertThat(preferences.readingSpoilers).isEqualTo(false)
    }
}
