package social.androiddev.splash.navigation

import com.arkivanov.decompose.ComponentContext

interface SplashComponent {

    fun isUserLoggedIn(loggedIn: Boolean)

    companion object {
        fun createDefaultComponent(
            componentContext: ComponentContext,
            navigateToWelcome: () -> Unit,
            navigateToTimeline: () -> Unit,
        ): SplashComponent = DefaultSplashComponent(
            componentContext = componentContext,
            navigateToTimeline = navigateToTimeline,
            navigateToWelcome = navigateToWelcome,
        )
    }
}

private class DefaultSplashComponent(
    componentContext: ComponentContext,
    private val navigateToWelcome: () -> Unit,
    private val navigateToTimeline: () -> Unit,
) : SplashComponent, ComponentContext by componentContext {
    override fun isUserLoggedIn(loggedIn: Boolean) {
        if (loggedIn) {
            navigateToTimeline()
        } else {
            navigateToWelcome()
        }
    }
}