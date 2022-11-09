package social.androiddev.common.composables

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.common.utils.AsyncImage
import social.androiddev.common.utils.loadImageIntoPainter

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    url: String
) {
    AsyncImage(
        load = { loadImageIntoPainter(url = url) },
        painterFor = { remember { it } },
        contentDescription = "User avatar",
        modifier = modifier.width(48.dp).clip(RoundedCornerShape(5.dp))
    )
}

// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
// @Preview
@Composable
private fun UserPreview() {
    MastodonTheme {
        UserAvatar(url = "https://media.mastodon.cloud/accounts/avatars/000/018/251/original/e78973b0b821c7e3.jpg")
    }
}
