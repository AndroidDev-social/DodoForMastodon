/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import social.androiddev.common.theme.DodoTheme
import social.androiddev.common.utils.AsyncImage

@Composable
fun UserAvatar(
    modifier: Modifier = Modifier,
    url: String
) {
    AsyncImage(
        url = url,
        contentDescription = "User avatar",
        modifier = modifier.width(48.dp).height(48.dp).clip(RoundedCornerShape(5.dp))
    )
}

// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
// @Preview
@Composable
private fun UserPreview() {
    DodoTheme {
        UserAvatar(url = "https://media.mastodon.cloud/accounts/avatars/000/018/251/original/e78973b0b821c7e3.jpg")
    }
}
