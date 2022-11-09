package social.androiddev.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import social.androiddev.common.composables.UserAvatar
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.domain.timeline.model.Account
import social.androiddev.domain.timeline.model.Status

@Composable
fun TimelineScreen(
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier.background(Color(0xFF292C36)).padding(8.dp).fillMaxSize()) {
        TimelineStatus(status = testStatus1)
    }
}

@Composable
fun TimelineStatus(
    modifier: Modifier = Modifier,
    status: Status
) {
    // TODO: overflow menu
    Row {
        UserAvatar(
            modifier = Modifier.padding(8.dp),
            url = status.account.avatar
        )
        Column {
            Row {
                Text(
                    text = status.account.username,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = status.account.acct,
                    color = Color(0xFF616981)
                )
                Row {
                    Text(
                        text = status.createdAt,
                        color = Color(0xFF616981)
                    )
                }
            }
            Text(status.content)
        }
    }
    // TODO: footer (reply, boost, favorite, share)
    status.repliesCount
    status.reblogsCount
    status.favouritesCount
    // share
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
        acct = "@bino@mastodon.cloud",
        avatar = "https://media.mastodon.cloud/accounts/avatars/000/018/251/original/e78973b0b821c7e3.jpg",
    ),
)


// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
//@Preview
@Composable
private fun TimelinePreview() {
    MastodonTheme {
        TimelineScreen()
    }
}
