package social.androiddev.welcome.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

internal class Root(
    componentContext: ComponentContext,
    deepLink: DeepLink = DeepLink.None,
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
            Config.Welcome -> RootComponent.Child.WelcomeChild(createWelcomeComponent(componentContext))
            Config.EnterServer -> RootComponent.Child.EnterServerChild
        }

    private fun createWelcomeComponent(
        componentContext: ComponentContext
    ): DefaultWelcomeComponent = DefaultWelcomeComponent(componentContext) {
        navigation.push(Config.EnterServer)
    }

    private fun getInitialStack(deepLink: DeepLink): List<Config> =
        when (deepLink) {
            is DeepLink.None -> listOf(Config.Welcome)
        }
    sealed interface DeepLink {
        object None : DeepLink
    }

    private sealed interface Config : Parcelable {

        @Parcelize
        object Welcome : Config

        @Parcelize
        object EnterServer : Config
    }
}

private class DefaultWelcomeComponent(
    componentContext: ComponentContext,
    private val onClick: () -> Unit
) : WelcomeComponent, ComponentContext by componentContext {
    override fun onGetStartedClicked() {
        onClick()
    }
}