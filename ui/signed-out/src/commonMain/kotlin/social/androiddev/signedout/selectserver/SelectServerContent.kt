/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.selectserver

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import social.androiddev.common.composables.buttons.DodoButton
import social.androiddev.common.composables.text.DodoTextField
import social.androiddev.common.theme.DodoTheme
import social.androiddev.common.web.WebOpenExtras

/**
 * Select Server view that delegates business/navigation logic to [SelectServerComponent]
 * for when a user wants to select their mastodon domain/server
 */
@Composable
fun SelectServerContent(
    modifier: Modifier,
    component: SelectServerComponent,
) {
    val state by component.state.collectAsState()
    val extras = webOpenExtras()

    SelectServerContent(
        modifier = modifier,
        loading = state.loading,
        error = state.error,
        onServerSelected = {
            component.onServerSelected(it, extras)
        },
        onCancel = component::onAuthCanceled
    )
}

@Composable
fun SelectServerContent(
    modifier: Modifier,
    loading: Boolean,
    error: String?,
    onServerSelected: (String) -> Unit,
    onCancel: () -> Unit,
) {

    var server by rememberSaveable { mutableStateOf("androiddev.social") }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        AnimatedVisibility(error != null) {
            ErrorBox(error.orEmpty())
        }

        DodoTextField(
            value = server,
            onValueChange = { v -> server = v },
        )

        Spacer(Modifier.height(24.dp))

        if (loading) {
            Row {
                CircularProgressIndicator()
                DodoButton(text = "Cancel", onClick = onCancel)
            }
        } else {
            DodoButton(
                text = "Select",
                onClick = { onServerSelected(server) },
            )
        }

    }
}

@Composable
fun ErrorBox(message: String) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .background(color = MaterialTheme.colors.error.copy(alpha = 0.5F))
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color =
                    MaterialTheme.colors.error
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            message,
            color = MaterialTheme.colors.onError,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
expect fun webOpenExtras(): WebOpenExtras

// @Preview
@Composable
private fun PreviewLandingContent() {
    DodoTheme(true) {
        SelectServerContent(
            modifier = Modifier.fillMaxSize(),
            loading = false,
            error = null,
            onServerSelected = {},
            onCancel = {},
        )
    }
}