package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNull

class ApplicationTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "name": "test app",
            "website": null,
            "vapid_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
        """.trimIndent()

        // when
        val application = Json.decodeFromString<Application>(json)

        // then
        assertEquals(expected = "test app", actual = application.name)
        assertNull(actual = application.website)
        assertEquals(expected = "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M=", actual = application.vapidKey)
        assertNull(actual = application.clientId)
        assertNull(actual = application.clientSecret)
    }
}
