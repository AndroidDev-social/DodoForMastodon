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

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

class DefaultSignedOutRootComponent(
    componentContext: ComponentContext,
    private val navigateToTimeLine: () -> Unit,
) : SignedOutRootComponent, ComponentContext by componentContext {

    // StackNavigation accepts navigation commands and forwards them to all subscribed observers.
    private val navigation = StackNavigation<Config>()

    // ChildStack is a simple data class that stores a stack of components and their configurations.
    private val stack: Value<ChildStack<Config, SignedOutRootComponent.Child>> = childStack(
        source = navigation,
        initialStack = { listOf(Config.Landing) },
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, SignedOutRootComponent.Child>> = stack

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): SignedOutRootComponent.Child =
        when (config) {
            Config.Landing -> {
                SignedOutRootComponent.Child.Landing(createLandingComponent(componentContext))
            }

            Config.SelectServer -> {
                SignedOutRootComponent.Child.SelectServer(
                    component = createSelectServerComponent(componentContext)
                )
            }

            is Config.SignIn -> {
                SignedOutRootComponent.Child.SignIn(
                    server = config.server,
                    component = createSignInComponent(componentContext)
                )
            }
        }

    private fun createLandingComponent(
        componentContext: ComponentContext
    ) = DefaultLandingComponent(
        componentContext = componentContext,
        onGetStartedClickedInternal = {
            navigation.push(Config.SelectServer)
        }
    )

    private fun createSelectServerComponent(
        componentContext: ComponentContext
    ) = DefaultSelectServerComponent(
        componentContext = componentContext,
        launchOAuth = { server ->
            navigation.push(Config.SignIn(server = server))
        }
    )

    private fun createSignInComponent(
        componentContext: ComponentContext
    ) = DefaultSignInComponent(
        componentContext = componentContext,
        onSignInSucceedInternal = navigateToTimeLine,
        onCloseClickedInternal = navigation::pop,
    )

    /**
     * Supported configurations for all children in this root.
     * A "configuration" represents a child component
     * and contains all it's arguments.
     * These configurations are persisted and restored on events like
     * configuration changes, process death etc...
     *
     * All Configurations must:
     * 1) Be immutable
     * 2) Implement `equals()` and `hashCode()`
     * 3) Implement Parcelable
     */
    private sealed interface Config : Parcelable {

        @Parcelize
        object Landing : Config

        @Parcelize
        object SelectServer : Config

        @Parcelize
        data class SignIn(val server: String) : Config
    }
}
