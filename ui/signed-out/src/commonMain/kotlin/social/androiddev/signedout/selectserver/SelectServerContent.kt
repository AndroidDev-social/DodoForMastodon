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
package social.androiddev.signedout.selectserver

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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

/**
 * Select Server view that delegates business/navigation logic to [SelectServerComponent]
 * for when a user wants to select their mastodon domain/server
 */
@Composable
fun SelectServerContent(
    component: SelectServerComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()

    SelectServerContent(
        modifier = modifier,
        btnEnabled = state.selectButtonEnabled,
        onServerSelected = component::onServerSelected,
    )
}

@Composable
fun SelectServerContent(
    btnEnabled: Boolean,
    onServerSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var server by rememberSaveable { mutableStateOf("androiddev.social") }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DodoTextField(
            value = server,
            onValueChange = { v -> server = v },
        )

        Spacer(Modifier.height(24.dp))

        DodoButton(
            text = "Select",
            enabled = btnEnabled,
            onClick = { onServerSelected(server) },
        )
    }
}

// @Preview
@Composable
private fun PreviewLandingContent() {
    DodoTheme(true) {
        SelectServerContent(
            modifier = Modifier.fillMaxSize(),
            btnEnabled = true,
            onServerSelected = {},
        )
    }
}
