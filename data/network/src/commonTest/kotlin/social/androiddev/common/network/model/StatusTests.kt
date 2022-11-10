package social.androiddev.common.network.model

import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class StatusTests {
    // TODO: fix loading json from resources
    @Ignore
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        // val json: String = javaClass.classLoader.getResource("response_status_required.json").readText()
        val json: String = ""

        // when
        val status = Json.decodeFromString<Status>(json)

        // then
        assertNotNull(actual = status)
        assertEquals(expected = "103270115826048975", actual = status.id)
    }
}
