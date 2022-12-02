/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.root.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import social.androiddev.root.navigation.SplashComponent

/**
 * Stateful SplashScreen composable using [SplashComponent] for
 * decompose navigation and business logic.
 */
@Composable
fun SplashContent(
    modifier: Modifier,
    component: SplashComponent,
) {
    SplashContent(
        modifier = modifier,
        navigateToWelcome = {
            component.navigateToLanding()
        },
        navigateToTimeline = {
            component.navigateToTimeline()
        },
    )
}

/**
 * Stateless composable for rendering a simple Splash Screen
 * upon app launch.
 */
@Composable
fun SplashContent(
    modifier: Modifier,
    navigateToTimeline: () -> Unit,
    navigateToWelcome: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Text("Loading")

        LaunchedEffect(Unit) {
            // TODO: Hook up to a DI ViewModel
            if (true) {
                navigateToWelcome()
            } else {
                navigateToTimeline()
            }
        }
    }
}
