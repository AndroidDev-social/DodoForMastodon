package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class HistoryTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(history.day).isEqualTo("1574553600")
        Truth.assertThat(history.uses).isEqualTo("200")
        Truth.assertThat(history.accounts).isEqualTo("31")
    }
}
