/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import social.androiddev.common.theme.MastodonTheme

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
                TimelineCard(
                    state = state,
                    modifier = Modifier.wrapContentSize(),
                )
            }
        }
    }
}

// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
// @Preview
@Composable
private fun TimelinePreview() {
    MastodonTheme {
        TimelineScreen(listOf(dummyFeedItem))
    }
}
