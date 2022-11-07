package social.androiddev.timeline

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.domain.timeline.model.Account
import social.androiddev.domain.timeline.model.Status

@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
) {
//    val interestsSelectionState by viewModel.interestsSelectionUiState.collectAsStateWithLifecycle()

    val statuses = listOf<Status>(testStatus1)
    // TODO: pull-2-refresh

    Text("This is a sample")

//    LazyColumn {
//        items(items = statuses) { status ->
//            TimelineStatus(status)
//        }
//    }
}

private val testStatus1 = Status(
    id = "1",
    createdAt = "2022-11-02T09:09:09.392Z",
    repliesCount = 9,
    reblogsCount = 12,
    favouritesCount = 15,
    content = "\uD83D\uDC4BHello #AndroidDev",
    account = Account(
        id = "1",
        username = "Benjamin Stürmer",
        acct = "bino",
        avatar = "https://media.mastodon.cloud/accounts/avatars/000/018/251/original/d1845b6fe035558c.jpg",
    ),
)

@Composable
fun TimelineStatus(
    status: Status
) {
    // TODO: header (avatar, name, handle, time since post, options)
    status.account.avatar
    status.account.username
    status.account.acct
    status.createdAt
    // options

    // TODO: content
    status.content

    // TODO: footer (reply, boost, favorite, share)
    status.repliesCount
    status.reblogsCount
    status.favouritesCount
    // share
}

@Preview
@Composable
private fun TimelinePreview() {
    MastodonTheme {
        TimelineScreen()
    }
}
