package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/emoji/
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
