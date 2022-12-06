/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
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
import social.androiddev.common.theme.DodoTheme
import social.androiddev.root.composables.RootContent
import social.androiddev.root.navigation.DefaultRootComponent

fun main() {

    val lifecycle = LifecycleRegistry()
    // Create the root component before starting Compose
    val root = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle)
    )

    application {
        val windowState = rememberWindowState()

        // Bind the registry to the lifecycle of the window
        LifecycleController(lifecycle, windowState)

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Dodo"
        ) {
            DodoTheme {
                RootContent(
                    component = root,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
