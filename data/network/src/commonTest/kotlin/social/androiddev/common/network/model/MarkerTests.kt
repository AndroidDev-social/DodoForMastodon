package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class MarkerTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json = """
        {
            "home":
            {
                "last_read_id": "103194548672408537",
                "version": 462,
                "updated_at": "2019-11-24T19:39:39.337Z"
            },
            "notifications":
            {
                "last_read_id": "35050107",
                "version": 356,
                "updated_at": "2019-11-25T13:47:31.333Z"
            }
        }
        """.trimIndent()

        // when
        val marker = Json.decodeFromString<Marker>(json)

        // then
        val homeHash = MarkerHash(
            lastReadId = "103194548672408537",
            version = 462,
            updatedAt = "2019-11-24T19:39:39.337Z"
        )
        val usesHash = MarkerHash(
            lastReadId = "35050107",
            version = 356,
            updatedAt = "2019-11-25T13:47:31.333Z"
        )
        assertEquals(expected = homeHash, actual = marker.home)
        assertEquals(expected = usesHash, actual = marker.uses)
    }
}
