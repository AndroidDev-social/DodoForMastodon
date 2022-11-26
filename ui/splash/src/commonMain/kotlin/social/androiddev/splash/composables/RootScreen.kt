/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.splash.composables

import WelcomeScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import social.androiddev.splash.navigation.RootComponent
import social.androiddev.splash.navigation.SplashComponent
import social.androiddev.welcome.navigation.WelcomeScreenComponent

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Children(
            stack = childStack,
            modifier = Modifier.fillMaxSize()
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is RootComponent.Child.SplashScreenChild -> SplashScreenNode(child.component)
                is RootComponent.Child.WelcomeScreenChild -> {
                    WelcomeScreenNode(child.component)
                }
                RootComponent.Child.TimelineScreenChild -> {
//                    TODO: TimelineScreen()
                }
                RootComponent.Child.SelectServerScreenChild -> {
//                    TODO: SelectServerScreen()
                }
            }
        }
    }
}

@Composable
private fun WelcomeScreenNode(component: WelcomeScreenComponent) {
    WelcomeScreen(
        modifier = Modifier.fillMaxSize(),
        component = component
    )
}

@Composable
private fun SplashScreenNode(component: SplashComponent) {
    SplashScreen(
        modifier = Modifier.fillMaxSize(),
        component = component
    )
}
