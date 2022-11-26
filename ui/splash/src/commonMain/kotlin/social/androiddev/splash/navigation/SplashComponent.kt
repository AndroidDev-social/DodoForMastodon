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
            navigateToTimelineScreenInternal = navigateToTimeline,
            navigateToWelcomeScreenInternal = navigateToWelcome,
        )
    }
}

private class DefaultSplashComponent(
    private val componentContext: ComponentContext,
    private val navigateToWelcomeScreenInternal: () -> Unit,
    private val navigateToTimelineScreenInternal: () -> Unit,
) : SplashComponent, ComponentContext by componentContext {

    override fun navigateToTimeline() {
        navigateToTimelineScreenInternal()
    }

    override fun navigateToWelcome() {
        navigateToWelcomeScreenInternal()
    }
}
