package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

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
        assertEquals(expected = "627", actual = featuredTag.id)
        assertEquals(expected = "nowplaying", actual = featuredTag.name)
        assertEquals(expected = 36, actual = featuredTag.statusesCount)
        assertEquals(expected = "2019-11-15T07:14:43.524Z", actual = featuredTag.lastStatusAt)
    }
}
