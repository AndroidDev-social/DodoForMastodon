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
package social.androiddev.root.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import social.androiddev.root.splash.SplashComponent
import social.androiddev.signedin.navigation.SignedInRootComponent
import social.androiddev.signedout.root.SignedOutRootComponent

/**
 * The base component describing all business logic needed for the root entry point
 */
interface RootComponent {

    // Store a stack of components and their configurations in this root graph
    val childStack: Value<ChildStack<*, Child>>

    /**
     * Supported "Child"s in this navigation stack. These are created from a configuration that
     * contains any arguments for this particular child in the navigation stack.
     */
    sealed class Child {
        data class Splash(val component: SplashComponent) : Child()

        data class SignedOut(val component: SignedOutRootComponent) : Child()

        data class SignedIn(val component: SignedInRootComponent) : Child()
    }

    /**
     * Supported deep links for this root graph
     */
    sealed interface DeepLink {
        object None : DeepLink
    }
}
