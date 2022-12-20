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
@file:Suppress("MaximumLineLength", "MaxLineLength")

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

class IdentityProofTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "provider": "Keybase",
            "provider_username": "gargron",
            "updated_at": "2019-07-21T20:14:39.596Z",
            "proof_url": "https://keybase.io/gargron/sigchain#5cfc20c7018f2beefb42a68836da59a792e55daa4d118498c9b1898de7e845690f",
            "profile_url": "https://keybase.io/gargron"
        }
        """.trimIndent()

        // when
        val identityProof = Json.decodeFromString<IdentityProof>(json)

        // then
        assertEquals(expected = "Keybase", actual = identityProof.provider)
        assertEquals(expected = "gargron", actual = identityProof.providerUsername)
        assertEquals(expected = "2019-07-21T20:14:39.596Z", actual = identityProof.updatedAt)
        assertEquals(
            expected = "https://keybase.io/gargron/sigchain#5cfc20c7018f2beefb42a68836da59a792e55daa4d118498c9b1898de7e845690f",
            actual = identityProof.proofUrl
        )
        assertEquals(expected = "https://keybase.io/gargron", actual = identityProof.profileUrl)
    }
}
