package social.androiddev.timeline

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
    items: List<FeedItemState>,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .background(Color(0xFF292C36))
            .padding(8.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items, key = { item -> item.id }) { state ->
                FeedCard(
                    state = state,
                    modifier = Modifier.wrapContentSize(),
                )
            }
        }
    }
}

// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
@Preview
@Composable
private fun TimelinePreview() {
    MastodonTheme {
        TimelineScreen(listOf(dummyFeedItem))
    }
}
