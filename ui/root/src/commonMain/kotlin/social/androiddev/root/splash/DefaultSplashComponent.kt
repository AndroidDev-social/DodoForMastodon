/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.root.splash

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.SupervisorJob
import org.koin.core.component.KoinComponent
import social.androiddev.common.decompose.coroutineScope
import kotlin.coroutines.CoroutineContext

class DefaultSplashComponent(
    private val componentContext: ComponentContext,
    private val mainContext: CoroutineContext,
    private val navigateToLandingInternal: () -> Unit,
    private val navigateToTimelineInternal: () -> Unit,
) : SplashComponent, KoinComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(mainContext + SupervisorJob())

    override fun navigateToTimeline() {
        navigateToTimelineInternal()
    }

    override fun navigateToLanding() {
        navigateToLandingInternal()
    }
}
