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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.StateFlow
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.common.theme.DodoTheme
import social.androiddev.domain.timeline.model.StatusUI
import social.androiddev.timeline.navigation.TimelineComponent

/**
 * Timeline view that delegates business/navigation logic to [TimelineComponent]
 * for when a user wants to view their Timeline
 */
@Composable
fun TimelineContent(
    state: StateFlow<StoreResponse<List<StatusUI>>>,
    modifier: Modifier = Modifier,
) {
   val items =  state.collectAsState()

   val feedItems =  when(val value = items.value){
        is StoreResponse.Data ->  {
            val feedItems =  value.value.map {
               FeedItemState(
                  id= it.remoteId,
                   userAvatarUrl = it.avatarUrl,
                   date = it.createdAt,
                   username = it.userName,
                   acctAddress = it.accountAddress,
                   message = it.content,
                   images = emptyList(),
                   videoUrl = null,
               )
            }

            TimelineContent(
                items = feedItems,
                modifier = modifier,
            )
        }
        else ->{

        }
    }
}

@Composable
fun TimelineContent(
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
    DodoTheme {
        TimelineContent(listOf(dummyFeedItem))
    }
}
