package social.androiddev.domain.timeline.model

data class Status(
    val id: String,
    val createdAt: String,
    val repliesCount: Int,
    val reblogsCount: Int,
    val favouritesCount: Int,
    val content: String,
    val account: Account,
    val sensitive: Boolean = false,
    val spoilerText: String? = null,
    val visibility: Visibility = Visibility.PUBLIC
)
