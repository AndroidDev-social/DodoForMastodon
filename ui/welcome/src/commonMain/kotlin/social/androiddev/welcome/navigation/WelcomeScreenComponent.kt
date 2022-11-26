package social.androiddev.welcome.navigation

import com.arkivanov.decompose.ComponentContext

/**
 * The base component describing all business logic needed for the welcome screen
 */
interface WelcomeScreenComponent {

    /**
     * Callback invoked when user clicks the "Get Started" button in the Welcome Screen
     */
    fun onGetStartedClicked()

    companion object {
        fun createDefaultComponent(
            componentContext: ComponentContext,
            navigateToEnterDomain: () -> Unit,
        ): WelcomeScreenComponent = DefaultSplashComponent(
            componentContext = componentContext,
            onGetStartedClickedInternal = navigateToEnterDomain,
        )
    }
}

private class DefaultSplashComponent(
    private val componentContext: ComponentContext,
    private val onGetStartedClickedInternal: () -> Unit,
) : WelcomeScreenComponent, ComponentContext by componentContext {
    override fun onGetStartedClicked() {
        onGetStartedClickedInternal()
    }
}