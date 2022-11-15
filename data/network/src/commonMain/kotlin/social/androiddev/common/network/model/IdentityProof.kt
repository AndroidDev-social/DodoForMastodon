/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
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
