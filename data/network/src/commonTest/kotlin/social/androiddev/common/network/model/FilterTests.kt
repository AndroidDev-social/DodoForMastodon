package social.androiddev.common.network.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class FilterTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "id": "8449",
            "phrase": "test",
            "context":
            [
                "home",
                "notifications",
                "public",
                "thread"
            ],
            "whole_word": false,
            "expires_at": "2019-11-26T09:08:06.254Z",
            "irreversible": true
        }
        """.trimIndent()

        // when
        val filter = Json.decodeFromString<Filter>(json)

        // then
        assertEquals(expected = "8449", actual = filter.id)
        assertEquals(expected = "test", actual = filter.phrase)
        assertEquals(
            expected = listOf(
                FilterContext.home, FilterContext.notifications, FilterContext.public, FilterContext.thread
            ), actual = filter.context
        )
        assertEquals(expected = false, actual = filter.wholeWord)
        assertEquals(expected = "2019-11-26T09:08:06.254Z", actual = filter.expiresAt)
        assertEquals(expected = true, actual = filter.irreversible)
    }
}
