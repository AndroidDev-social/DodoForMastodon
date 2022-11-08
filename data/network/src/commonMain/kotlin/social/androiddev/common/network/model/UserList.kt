package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/list/
 */
@Serializable
data class UserList(
    // required attributes
    @SerialName("id") val id: String,
    @SerialName("title") val title: String,
    @SerialName("replies_policy") val repliesPolicy: RepliesPolicy,
)

enum class RepliesPolicy { followed, list, none }
