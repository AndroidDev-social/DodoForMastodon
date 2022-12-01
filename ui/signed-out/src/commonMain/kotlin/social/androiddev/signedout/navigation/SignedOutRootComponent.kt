/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

/**
 * The base component describing all business logic needed for the signed-out root entry point
 */
interface SignedOutRootComponent {

    // Store a stack of components and their configurations in this root graph
    val childStack: Value<ChildStack<*, Child>>

    /**
     * Supported "Child"s in this navigation stack. These are created from a configuration that
     * contains any arguments for this particular child in the navigation stack.
     */
    sealed class Child {

        data class Landing(val component: LandingComponent) : Child()

        data class SelectServer(val component: SelectServerComponent) : Child()

        data class SignIn(val server: String, val component: SignInComponent) : Child()
    }
}
