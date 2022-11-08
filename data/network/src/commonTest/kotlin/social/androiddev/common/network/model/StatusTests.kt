package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class StatusTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json: String = javaClass.classLoader.getResource("response_status_required.json").readText()

        // when
        val status = Json.decodeFromString<Status>(json)

        // then
        Truth.assertThat(status).isNotNull()
        Truth.assertThat(status.id).isEqualTo("103270115826048975")
    }
}
