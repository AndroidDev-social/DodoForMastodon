/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.composables.webview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel

@Composable
actual fun SignInWebView(
    server: String,
    onSingedIn: () -> Unit,
    onFailed: (error: String) -> Unit
) {
    val signInViewModel = remember(server) { SignInViewModel(server) }

    SwingPanel(
        background = MaterialTheme.colors.surface,
        factory = {
            JFXWebView(
                url = signInViewModel.getSignInUrl(),
                onUrlOfCurrentPageChanged = {
                    signInViewModel.resolveSignInStatusFromUrl(
                        url = it,
                        onSingedIn = onSingedIn,
                        onFailed = onFailed
                    )
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    )
}
