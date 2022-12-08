package social.androiddev.common.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableInstance(
    val domain: String,
    val version: String,
    val description: String,
    val languages: List<String>,
    val region: String,
    val categories: List<String>,
    @SerialName("proxied_thumbnail") val thumbnail: String,
    @SerialName("total_users") val totalUsers: Int,
    @SerialName("last_week_users") val lastWeekUsers: Int,
    @SerialName("approval_required") val approvalRequired: Boolean,
    val language: String,
    val category: String,
)
