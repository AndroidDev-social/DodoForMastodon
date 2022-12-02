/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import social.androiddev.signedout.navigation.SignedOutRootComponent

/**
 * The root composable for when the user launches the app and is
 * currently signed out.
 * Business logic and decompose navigation is delegated to [SignedOutRootComponent].
 */
@Composable
fun SignedOutRootContent(
    component: SignedOutRootComponent,
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
                is SignedOutRootComponent.Child.Landing -> {
                    LandingContent(
                        modifier = Modifier.fillMaxSize(),
                        component = child.component,
                    )
                }

                is SignedOutRootComponent.Child.SelectServer -> {
                    SelectServerContent(
                        modifier = Modifier.fillMaxSize(),
                        component = child.component,
                    )
                }
            }
        }
    }
}
