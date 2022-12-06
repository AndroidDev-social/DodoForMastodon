/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/poll/
 */
@Serializable
data class Poll(
    @SerialName("id") val id: String,
    @SerialName("expires_at") val expiresAt: String,
    @SerialName("expired") val expired: Boolean,
    @SerialName("multiple") val multiple: Boolean,
    @SerialName("votes_count") val votesCount: Int? = null,
    @SerialName("voters_count") val votersCount: Int? = null,
    @SerialName("voted") val voted: Boolean? = null,
    @SerialName("own_votes") val ownVotes: List<Int>? = null,
    @SerialName("options") val options: List<PollHash>? = null,
    @SerialName("emojis") val emojis: List<Emoji>? = null,
)

@Serializable
data class PollHash(
    val title: String,
    @SerialName("votes_count") val votesCount: Int,
)
