/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.root.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import social.androiddev.root.navigation.RootComponent
import social.androiddev.root.navigation.SplashComponent
import social.androiddev.signedin.composables.SignedInRootContent
import social.androiddev.signedin.navigation.SignedInRootComponent
import social.androiddev.signedout.composables.SignedOutRootContent
import social.androiddev.signedout.navigation.SignedOutRootComponent

/**
 * App root composable that delegates business logic
 * and decompose navigation to [RootComponent]
 */
@OptIn(ExperimentalDecomposeApi::class)
@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    val childStack by component.childStack.subscribeAsState()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {

        Children(
            stack = childStack,
            modifier = Modifier.fillMaxSize(),
        ) { createdChild ->
            when (val child = createdChild.instance) {
                is RootComponent.Child.Splash -> {
                    SplashScreen(
                        component = child.component,
                    )
                }

                is RootComponent.Child.SignedIn -> {
                    SignedInRoot(
                        component = child.component,
                    )
                }

                is RootComponent.Child.SignedOut -> {
                    SignedOutRoot(
                        component = child.component,
                    )
                }
            }
        }
    }
}

@Composable
private fun SignedOutRoot(
    component: SignedOutRootComponent,
) {
    SignedOutRootContent(
        modifier = Modifier.fillMaxSize(),
        component = component,
    )
}

@Composable
private fun SignedInRoot(
    component: SignedInRootComponent,
) {
    SignedInRootContent(
        modifier = Modifier.fillMaxSize(),
        component = component,
    )
}

@Composable
private fun SplashScreen(
    component: SplashComponent,
) {
    SplashContent(
        modifier = Modifier.fillMaxSize(),
        component = component,
    )
}
