/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
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
