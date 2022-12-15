/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.root.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import social.androiddev.root.navigation.DefaultRootComponent.Config
import social.androiddev.signedin.navigation.DefaultSignedInRootComponent
import social.androiddev.signedout.root.DefaultSignedOutRootComponent
import kotlin.coroutines.CoroutineContext

/**
 * Default impl of the [RootComponent] that manages the navigation stack for the
 * 3 main "scopes". Loading/Splash screen, Logged out flow, and Logged in flow.
 * See [Config] and [RootComponent.Child] for more details.
 */
class DefaultRootComponent(
    componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    deepLink: RootComponent.DeepLink = RootComponent.DeepLink.None,
) : RootComponent, ComponentContext by componentContext {

    // StackNavigation accepts navigation commands and forwards them to all subscribed observers.
    private val navigation = StackNavigation<Config>()

    // ChildStack is a simple data class that stores a stack of components and their configurations.
    private val stack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        initialStack = { getInitialStack(deepLink) },
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createChild,
    )

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = stack

    private fun createChild(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            Config.Splash -> RootComponent.Child.Splash(createSplashComponent(componentContext))
            Config.SignedIn -> RootComponent.Child.SignedIn(createSignedInComponent(componentContext))
            Config.SignedOut -> RootComponent.Child.SignedOut(createSignedOutComponent(componentContext))
        }

    private fun createSignedOutComponent(
        componentContext: ComponentContext,
    ) = DefaultSignedOutRootComponent(
        componentContext = componentContext,
        mainContext = mainContext,
        navigateToTimeLine = {
            navigation.replaceAll(Config.SignedIn)
        },
    )

    private fun createSignedInComponent(
        componentContext: ComponentContext,
    ) = DefaultSignedInRootComponent(
        componentContext = componentContext,
        mainContext = mainContext
    )

    private fun createSplashComponent(
        componentContext: ComponentContext,
    ) = DefaultSplashComponent(
        componentContext = componentContext,
        navigateToTimelineInternal = {
            navigation.replaceCurrent(Config.SignedIn)
        },
        navigateToLandingInternal = {
            navigation.replaceCurrent(Config.SignedOut)
        },
    )

    private fun getInitialStack(
        deepLink: RootComponent.DeepLink,
    ): List<Config> =
        when (deepLink) {
            is RootComponent.DeepLink.None -> listOf(Config.Splash)
        }

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
        object SignedIn : Config

        @Parcelize
        object SignedOut : Config

        @Parcelize
        object Splash : Config
    }
}
