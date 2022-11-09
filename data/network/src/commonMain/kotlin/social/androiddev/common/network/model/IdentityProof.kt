package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/identityproof/
 */
@Serializable
data class IdentityProof(
    // required attributes
    @SerialName("provider") val provider: String,
    @SerialName("provider_username") val providerUsername: String,
    @SerialName("profile_url") val profileUrl: String,
    @SerialName("proof_url") val proofUrl: String,
    @SerialName("updated_at") val updatedAt: String,
)
