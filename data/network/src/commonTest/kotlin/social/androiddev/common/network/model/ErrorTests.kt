package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ErrorTests {
    @Test
    fun `deserialize video card should succeed`() = runBlocking {
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
        Truth.assertThat(error.error).isEqualTo("invalid_grant")
        Truth.assertThat(error.errorDescription).isEqualTo("The provided authorization grant is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client.")
    }
}
