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
package social.androiddev.signedin.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.StateFlow
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.settings.SettingsContent
import social.androiddev.signedin.navigation.NavHeaderSettings
import social.androiddev.signedin.navigation.SignedInRootComponent
import social.androiddev.timeline.FeedItemState
import social.androiddev.timeline.TimelineContent

/**
 * The root composable for when the user launches the app and is
 * currently signed in.
 * Business logic and decompose navigation is delegated to [SignedInRootComponent].
 */
@Composable
fun SignedInRootContent(
    component: SignedInRootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()
    var headerSettings by remember { mutableStateOf<NavHeaderSettings>(NavHeaderSettings.Empty) }

    LaunchedEffect(childStack.active) {
        headerSettings = when (childStack.active.instance) {
            is SignedInRootComponent.Child.Settings -> {
                NavHeaderSettings.Settings
            }

            is SignedInRootComponent.Child.Timeline -> {
                NavHeaderSettings.Timeline(action = component::navigateToSettings)
            }
        }
    }
    Column(
        modifier = modifier,
    ) {
        TopAppBar(
            title = { Text(headerSettings.title) },
            actions = {
                headerSettings.headerAction?.let { header ->
                    IconButton(onClick = header.action) {
                        Icon(header.icon, null)
                    }
                }
            },
            navigationIcon = if (childStack.backStack.isNotEmpty()) (
                {
                    IconButton(onClick = component::navigateBack) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
                ) else null
        )
        Children(
            stack = childStack,
            modifier = Modifier.fillMaxSize()
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is SignedInRootComponent.Child.Timeline -> {
                    TimelineTab(child.component.state)
                }

                is SignedInRootComponent.Child.Settings -> {
                    SettingsContent(component = child.component)
                }
            }
        }
    }
}

@Composable
private fun TimelineTab(
    state: StateFlow<StoreResponse<ImmutableList<FeedItemState>>>,
) {
    TimelineContent(
        state = state,
        modifier = Modifier.fillMaxSize(),
    )
}
