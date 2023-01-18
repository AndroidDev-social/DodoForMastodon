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
package social.androiddev.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import social.androiddev.common.composables.buttons.DodoButton
import social.androiddev.common.composables.buttons.buttonColors

@Composable
fun SettingsContent(
    component: SettingsComponent,
    modifier: Modifier = Modifier,
) {
    SettingsContent(
        modifier = modifier,
        onLogout = component::logout,
    )
}

@Composable
internal fun SettingsContent(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        DodoButton(
            text = "Logout",
            onClick = onLogout,
            colors = buttonColors(backgroundColor = MaterialTheme.colors.error)
        )
    }
}
