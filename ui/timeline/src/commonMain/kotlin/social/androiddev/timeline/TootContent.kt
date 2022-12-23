/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.timeline

// import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import social.androiddev.common.theme.DodoTheme

@Composable
fun TootContent(
    username: String,
    userAddress: String,
    message: AnnotatedString?,
    date: String,
    videoUrl: String?,
    images: ImmutableList<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        TootContentHeader(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            username = username,
            userAddress = userAddress,
            date = date,
        )
        VerticalSpacer()
        // TODO Add support for video + multiple images rendering
        // for now just show message from toot
        if (message != null) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = message,
                style = MaterialTheme.typography.caption
            )
            VerticalSpacer()
        }
    }
}

// @Preview
@Composable
private fun PreviewTootContentLight() {
    DodoTheme(useDarkTheme = false) {
        Surface {
            TootContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                username = "@Omid",
                userAddress = "@omid@androiddev.social",
                message = AnnotatedString("\uD83D\uDC4BHello #AndroidDev"),
                date = "1d",
                images = persistentListOf(),
                videoUrl = null
            )
        }
    }
}

// @Preview
@Composable
private fun PreviewTootContentDark() {
    DodoTheme(useDarkTheme = true) {
        Surface {
            TootContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                username = "@Omid",
                userAddress = "@omid@androiddev.social",
                message = AnnotatedString("\uD83D\uDC4BHello #AndroidDev"),
                date = "1d",
                images = persistentListOf(),
                videoUrl = null
            )
        }
    }
}
