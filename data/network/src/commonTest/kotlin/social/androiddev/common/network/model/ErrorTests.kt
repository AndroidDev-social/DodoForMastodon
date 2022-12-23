/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

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
        assertEquals(
            expected = "The provided authorization grant is invalid, expired, " +
                "revoked, does not match the redirection URI used " +
                "in the authorization request, or was issued to another client.",
            actual = error.errorDescription
        )
    }
}
