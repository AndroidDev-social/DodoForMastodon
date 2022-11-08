package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class IdentityProofTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(identityProof.provider).isEqualTo("Keybase")
        Truth.assertThat(identityProof.providerUsername).isEqualTo("gargron")
        Truth.assertThat(identityProof.updatedAt).isEqualTo("2019-07-21T20:14:39.596Z")
        Truth.assertThat(identityProof.proofUrl).isEqualTo("https://keybase.io/gargron/sigchain#5cfc20c7018f2beefb42a68836da59a792e55daa4d118498c9b1898de7e845690f")
        Truth.assertThat(identityProof.profileUrl).isEqualTo("https://keybase.io/gargron")
    }
}
