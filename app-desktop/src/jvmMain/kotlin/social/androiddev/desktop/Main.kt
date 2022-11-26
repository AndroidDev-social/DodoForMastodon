/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.desktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.splash.composables.RootScreen
import social.androiddev.splash.navigation.RootComponent

fun main() {

    val lifecycle = LifecycleRegistry()
    // Create the root component before starting Compose
    val root = RootComponent.createDefaultComponent(
        componentContext = DefaultComponentContext(lifecycle)
    )

    application {
        val windowState = rememberWindowState()

        // Bind the registry to the lifecycle of the window
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "MastodonX"
        ) {
            MastodonTheme {
                RootScreen(
                    component = root,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
