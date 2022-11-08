package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class FilterTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(filter.id).isEqualTo("8449")
        Truth.assertThat(filter.phrase).isEqualTo("test")
        Truth.assertThat(filter.context).isEqualTo(
            listOf(
                FilterContext.home,
                FilterContext.notifications,
                FilterContext.public,
                FilterContext.thread
            )
        )
        Truth.assertThat(filter.wholeWord).isEqualTo(false)
        Truth.assertThat(filter.expiresAt).isEqualTo("2019-11-26T09:08:06.254Z")
        Truth.assertThat(filter.irreversible).isEqualTo(true)
    }
}
