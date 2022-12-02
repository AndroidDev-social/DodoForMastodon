/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.timeline

// import androidx.compose.desktop.ui.tooling.preview.Preview
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
import social.androiddev.common.theme.DodoTheme

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

// @Preview
@Composable
private fun PreviewLight() {
    DodoTheme(useDarkTheme = false) {
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

// @Preview
@Composable
private fun PreviewDark() {
    DodoTheme(useDarkTheme = true) {
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
