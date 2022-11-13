package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ErrorTests {
    @Test
    fun `deserialize video card should succeed`() {
        // given
        val json = """
        {
            "error": "invalid_grant",
            "error_description": "The provided authorization grant is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client."
        }
        """.trimIndent()

        // when
        val error = Json.decodeFromString<Error>(json)

        // then
        assertEquals(expected = "invalid_grant", actual = error.error)
        assertEquals(expected = "The provided authorization grant is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client.", actual = error.errorDescription)
    }
}
