package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.goncalossilva.resources.Resource
import kotlin.test.Test

class StatusTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json: String = Resource("src/commonTest/resources/response_status_required.json").readText()

        // when
        val status = Json.decodeFromString<Status>(json)

        // then
        assertNotNull(actual = status)
        assertEquals(expected = "103270115826048975", actual = status.id)
    }
}
