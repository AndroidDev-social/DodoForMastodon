package social.androiddev.common.composables

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.dp
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.common.utils.AsyncImage
import social.androiddev.common.utils.loadImageBitmap

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    url: String
) {
    AsyncImage(
        load = { loadImageBitmap(url = url) },
        painterFor = { remember { BitmapPainter(it) } },
        contentDescription = "User avatar",
        modifier = modifier.width(48.dp).clip(RoundedCornerShape(5.dp))
    )
}

@Preview
@Composable
private fun UserPreview() {
    MastodonTheme {
        UserAvatar(url = "https://media.mastodon.cloud/accounts/avatars/000/018/251/original/e78973b0b821c7e3.jpg")
    }
}
