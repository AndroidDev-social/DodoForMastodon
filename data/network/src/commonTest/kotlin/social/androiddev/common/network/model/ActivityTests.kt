package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ActivityTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "week": "1574640000",
            "statuses": "37125",
            "logins": "14239",
            "registrations": "542"
        }
        """.trimIndent()

        // when
        val activity = Json.decodeFromString<Activity>(json)

        // then
        assertEquals(expected = "1574640000", actual = activity.week)
        assertEquals(expected = "37125", actual = activity.statuses)
        assertEquals(expected = "14239", actual = activity.logins)
        assertEquals(expected = "542", actual = activity.registrations)
    }
}
