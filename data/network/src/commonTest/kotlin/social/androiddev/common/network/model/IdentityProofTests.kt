package social.androiddev.common.network.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

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
