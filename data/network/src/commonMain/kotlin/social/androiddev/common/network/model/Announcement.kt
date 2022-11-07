package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://docs.joinmastodon.org/entities/announcement/
 */
@Serializable
data class Announcement(
    // Base attributes
    @SerialName("id") val id: String,
    @SerialName("text") val text: String,
    @SerialName("published") val published: Boolean,
    @SerialName("all_day") val allDay: Boolean,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
    @SerialName("read") val read: Boolean,
    @SerialName("reactions") val reactions: List<AnnouncementReaction>,

    // Optional attributes
    @SerialName("scheduled_at") val scheduledAt: String? = null,
    @SerialName("starts_at") val startsAt: String? = null,
    @SerialName("ends_at") val endsAt: String? = null,
)
