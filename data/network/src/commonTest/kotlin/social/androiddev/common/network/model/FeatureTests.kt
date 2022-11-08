package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class FeatureTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json = """
        {
            "id": "627",
            "name": "nowplaying",
            "url": "",
            "statuses_count": 36,
            "last_status_at": "2019-11-15T07:14:43.524Z"
        }
        """.trimIndent()

        // when
        val featuredTag = Json.decodeFromString<FeaturedTag>(json)

        // then
        Truth.assertThat(featuredTag.id).isEqualTo("627")
        Truth.assertThat(featuredTag.name).isEqualTo("nowplaying")
        Truth.assertThat(featuredTag.statusesCount).isEqualTo(36)
        Truth.assertThat(featuredTag.lastStatusAt).isEqualTo("2019-11-15T07:14:43.524Z")
    }
}
