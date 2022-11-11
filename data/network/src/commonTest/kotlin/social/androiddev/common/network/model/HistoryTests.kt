package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class HistoryTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "day": "1574553600",
            "uses": "200",
            "accounts": "31"
        }
        """.trimIndent()

        // when
        val history = Json.decodeFromString<History>(json)

        // then
        assertEquals(expected = "1574553600", actual = history.day)
        assertEquals(expected = "200", actual = history.uses)
        assertEquals(expected = "31", actual = history.accounts)
    }
}
