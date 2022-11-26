package social.androiddev.splash.navigation

import com.arkivanov.decompose.ComponentContext

/**
 * The base component describing all business logic needed for the splash screen
 */
interface SplashComponent {

    /**
     * Callback invoked when the logged-in user should be taken to the timeline screen
     */
    fun navigateToTimeline()

    /**
     * Callback invoked when the logged-out user should be taken to the welcome screen
     */
    fun navigateToWelcome()

    companion object {
        fun createDefaultComponent(
            componentContext: ComponentContext,
            navigateToWelcome: () -> Unit,
            navigateToTimeline: () -> Unit,
        ): SplashComponent = DefaultSplashComponent(
            componentContext = componentContext,
            navigateToTimelineScreen = navigateToTimeline,
            navigateToWelcomeScreen = navigateToWelcome,
        )
    }
}

private class DefaultSplashComponent(
    private val componentContext: ComponentContext,
    private val navigateToWelcomeScreen: () -> Unit,
    private val navigateToTimelineScreen: () -> Unit,
) : SplashComponent, ComponentContext by componentContext {

    override fun navigateToTimeline() {
        navigateToTimelineScreen()
    }

    override fun navigateToWelcome() {
        navigateToWelcomeScreen()
    }
}