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
package social.androidev.composetoot

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.AttachFile
import androidx.compose.material.icons.outlined.Feedback
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Mood
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import social.androiddev.common.composables.buttons.DodoButton
import social.androiddev.common.theme.DodoTheme
import social.androiddev.common.utils.AsyncImage
import social.androiddev.common.utils.loadImageIntoPainter

@Composable
fun ComposeTootContent(
    component: ComposeTootComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()

    ComposeTootContent(
        modifier = modifier.fillMaxSize(),
        composeTootState = state,
        onCloseClicked = component::onCloseClicked,
        onTootContentChange = component::onTootContentChange,
        onActionClicked = component::onActionClicked,
        onPostClicked = component::onPostClicked,
    )
}

@Composable
private fun ComposeTootContent(
    composeTootState: ComposeTootState,
    onCloseClicked: () -> Unit,
    onTootContentChange: (String) -> Unit,
    onActionClicked: (Action) -> Unit,
    onPostClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header(
                modifier = Modifier.fillMaxWidth(),
                currentUser = composeTootState.currentUser,
                onCloseClicked = onCloseClicked,
                onActionClicked = onActionClicked,
            )

            ComposeTootArea(
                modifier = Modifier.weight(1f),
                content = composeTootState.content,
                onTootContentChange = onTootContentChange
            )

            Footer(
                modifier = Modifier.fillMaxWidth(),
                tootTextCounter = composeTootState.tootTextCounter,
                postTootEnabled = composeTootState.postTootEnabled,
                selectedAction = composeTootState.selectedAction,
                onPostClicked = onPostClicked,
                onActionClicked = onActionClicked,
            )
        }
    }
}

@Composable
private fun ComposeTootArea(
    content: Content,
    onTootContentChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = content.toot,
            onValueChange = onTootContentChange,
            placeholder = { Text("What's happening?") }
        )
    }
}

@Composable
private fun Header(
    currentUser: CurrentUser,
    onCloseClicked: () -> Unit,
    onActionClicked: (Action) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
    ) {
        IconButton(onClick = onCloseClicked) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close"
            )
        }

        Spacer(
            modifier = Modifier.weight(1F)
        )

        ComposeTootIconButton(
            onClick = { onActionClicked(Action.AddHashtag) },
            icon = Icons.Outlined.Tag,
            description = "TAG",
            selected = false
        )

        ComposeTootIconButton(
            onClick = { onActionClicked(Action.AddMention) },
            icon = Icons.Outlined.AlternateEmail,
            description = "Mention",
            selected = false
        )

        ComposeTootIconButton(
            onClick = { onActionClicked(Action.ChooseLanguage) },
            icon = Icons.Outlined.Language,
            description = "Choose language",
            selected = false
        )

        AsyncImage(
            load = { loadImageIntoPainter(url = currentUser.avatarUrl) },
            painterFor = { remember { it } },
            contentDescription = currentUser.displayName,
            modifier = Modifier
                .size(48.dp)
                .padding(2.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
private fun Footer(
    tootTextCounter: String,
    postTootEnabled: Boolean,
    selectedAction: Action?,
    onActionClicked: (Action) -> Unit,
    onPostClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        BottomAppBar {
            ComposeTootIconButton(
                onClick = { onActionClicked(Action.AddMedia) },
                icon = Icons.Outlined.AttachFile,
                description = "Add Media",
                selected = selectedAction == Action.AddMedia
            )

            ComposeTootIconButton(
                onClick = { onActionClicked(Action.ChangeVisibility) },
                icon = Icons.Outlined.Public,
                description = "Toot Visibility",
                selected = selectedAction == Action.ChangeVisibility
            )

            ComposeTootIconButton(
                onClick = { onActionClicked(Action.AddContentWarning) },
                icon = Icons.Outlined.Feedback,
                description = "Content Warning",
                selected = selectedAction == Action.AddContentWarning
            )

            ComposeTootIconButton(
                onClick = { onActionClicked(Action.AddEmoji) },
                icon = Icons.Outlined.Mood,
                description = "Emoji",
                selected = selectedAction == Action.AddEmoji
            )

            ComposeTootIconButton(
                onClick = { onActionClicked(Action.Schedule) },
                icon = Icons.Outlined.Schedule,
                description = "Schedule",
                selected = selectedAction == Action.Schedule
            )

            Spacer(
                modifier = Modifier.weight(1F)
            )

            Text(
                text = tootTextCounter
            )

            Spacer(
                modifier = Modifier.width(4.dp)
            )

            DodoButton(
                text = "Post",
                enabled = postTootEnabled,
                onClick = onPostClicked
            )
        }

        AnimatedVisibility(selectedAction != null) {
            Surface(
                elevation = AppBarDefaults.BottomAppBarElevation
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Feature not yet Available !"
                    )
                }
            }
        }
    }
}

@Composable
private fun ComposeTootIconButton(
    onClick: () -> Unit,
    icon: ImageVector,
    description: String,
    selected: Boolean
) {
    val backgroundModifier = if (selected) {
        Modifier.background(
            color = LocalContentColor.current,
            shape = CircleShape
        )
    } else {
        Modifier
    }

    IconButton(
        onClick = onClick,
        modifier = backgroundModifier
    ) {
        val tint = if (selected) {
            MaterialTheme.colors.primary
        } else {
            LocalContentColor.current
        }
        Icon(
            icon,
            tint = tint,
            modifier = Modifier.padding(8.dp),
            contentDescription = description
        )
    }
}

// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
// @Preview
@Composable
private fun ComposeTootContentPreview() {
    DodoTheme {
        var state by remember {
            mutableStateOf(
                ComposeTootState(
                    content = Content(
                        toot = "",
                        warning = null
                    ),
                    currentUser = CurrentUser(
                        displayName = "Mohammed",
                        avatarUrl = "https://cdn.masto.host/androiddevsocial/accounts/avatars/" +
                            "109/412/078/242/933/246/original/dc1c7b288e98c67e.jpeg"

                    ),
                    tootTextCounter = "500",
                    postTootEnabled = false,
                )
            )
        }

        ComposeTootContent(
            modifier = Modifier.fillMaxSize(),
            composeTootState = state,
            onCloseClicked = {
            },
            onActionClicked = {
            },
            onTootContentChange = {
                state = state.copy(content = state.content.copy(toot = it))
            },
            onPostClicked = {},
        )
    }
}
