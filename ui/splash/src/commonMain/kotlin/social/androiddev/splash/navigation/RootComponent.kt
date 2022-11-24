package social.androiddev.splash.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class SplashScreenChild(val component: SplashComponent) : Child()
        object LoggedOutChild : Child()
        object TimelineChild : Child()
    }

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

internal class DefaultRootComponent(
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
            Config.SplashScreen -> {
                RootComponent.Child.SplashScreenChild(createSplashComponent(componentContext))
            }

            Config.LoggedOut -> RootComponent.Child.LoggedOutChild
            Config.Timeline -> RootComponent.Child.TimelineChild
        }

    private fun createSplashComponent(
        componentContext: ComponentContext
    ) = SplashComponent.createDefaultComponent(
        componentContext = componentContext,
        navigateToTimeline = {
            navigation.push(Config.Timeline)
        },
        navigateToWelcome = {
            navigation.push(Config.LoggedOut)
        }
    )

    private fun getInitialStack(deepLink: RootComponent.DeepLink): List<Config> =
        when (deepLink) {
            is RootComponent.DeepLink.None -> listOf(Config.SplashScreen)
        }

    private sealed interface Config : Parcelable {

        @Parcelize
        object SplashScreen : Config

        @Parcelize
        object LoggedOut : Config

        @Parcelize
        object Timeline : Config
    }
}