package social.androiddev.timeline

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import social.androiddev.common.theme.MastodonTheme

@Composable
fun TootContentHeader(
    modifier: Modifier,
    username: String,
    userAddress: String,
    date: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = username,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.subtitle1
        )

        Text(
            text = userAddress,
            style = MaterialTheme.typography.caption
        )

        HorizontalSpacer()

        Text(
            text = date,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.overline
        )
    }
}

@Preview
@Composable
private fun PreviewLight() {
    MastodonTheme(useDarkTheme = false) {
        Surface {
            TootContentHeader(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                username = "@Omid",
                userAddress = "@omid@androiddev.social",
                date = "1d",
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDark() {
    MastodonTheme(useDarkTheme = true) {
        Surface {
            TootContentHeader(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                username = "@Omid",
                userAddress = "@omid@androiddev.social",
                date = "1d",
            )
        }
    }
}