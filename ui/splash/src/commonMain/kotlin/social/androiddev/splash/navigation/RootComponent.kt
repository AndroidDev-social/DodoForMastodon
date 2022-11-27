/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.splash.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import social.androiddev.welcome.navigation.WelcomeScreenComponent

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
        data class SplashScreenChild(val component: SplashComponent) : Child()

        data class WelcomeScreenChild(val component: WelcomeScreenComponent) : Child()

        object TimelineScreenChild : Child()

        object SelectServerScreenChild : Child()
    }

    /**
     * Supported deep links for this root graph
     */
    sealed interface DeepLink {
        object None : DeepLink
    }

    companion object {

        fun createDefaultComponent(
            componentContext: ComponentContext,
            deepLink: DeepLink = DeepLink.None,
        ): RootComponent = DefaultRootComponent(
            componentContext = componentContext,
            deepLink = deepLink
        )
    }
}

private class DefaultRootComponent(
    componentContext: ComponentContext,
    deepLink: RootComponent.DeepLink = RootComponent.DeepLink.None,
) : RootComponent, ComponentContext by componentContext {

    // StackNavigation accepts navigation commands and forwards them to all subscribed observers.
    private val navigation = StackNavigation<Config>()

    // ChildStack is a simple data class that stores a stack of components and their configurations.
    private val stack: Value<ChildStack<Config, RootComponent.Child>> = childStack(
        source = navigation,
        initialStack = { getInitialStack(deepLink) },
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = stack

    private fun createChild(config: Config, componentContext: ComponentContext): RootComponent.Child =
        when (config) {
            Config.SplashScreen -> RootComponent.Child.SplashScreenChild(createSplashComponent(componentContext))
            Config.WelcomeScreen -> RootComponent.Child.WelcomeScreenChild(createWelcomeComponent(componentContext))
            Config.TimelineGraph -> RootComponent.Child.TimelineScreenChild
            Config.SelectServerScreen -> RootComponent.Child.SelectServerScreenChild
        }

    private fun createWelcomeComponent(
        componentContext: ComponentContext
    ) = WelcomeScreenComponent.createDefaultComponent(
        componentContext = componentContext,
        navigateToEnterDomain = {
            navigation.push(Config.SelectServerScreen)
        }
    )

    private fun createSplashComponent(
        componentContext: ComponentContext
    ) = SplashComponent.createDefaultComponent(
        componentContext = componentContext,
        navigateToTimeline = {
            navigation.replaceCurrent(Config.WelcomeScreen)
        },
        navigateToWelcome = {
            navigation.replaceCurrent(Config.WelcomeScreen)
        }
    )

    private fun getInitialStack(deepLink: RootComponent.DeepLink): List<Config> =
        when (deepLink) {
            is RootComponent.DeepLink.None -> listOf(Config.SplashScreen)
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
        object SplashScreen : Config

        @Parcelize
        object WelcomeScreen : Config

        @Parcelize
        object SelectServerScreen : Config

        @Parcelize
        object TimelineGraph : Config
    }
}
