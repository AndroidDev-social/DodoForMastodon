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

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import social.androiddev.domain.authentication.usecase.GetSelectedApplicationOAuthToken
import kotlin.coroutines.CoroutineContext

internal class SplashViewModel(
    mainContext: CoroutineContext,
    private val getAuthToken: GetSelectedApplicationOAuthToken
) : InstanceKeeper.Instance {
    private val scope = CoroutineScope(mainContext + SupervisorJob())

    private val _state: MutableStateFlow<SplashComponent.State> =
        MutableStateFlow(SplashComponent.State.Loading)

    val state: StateFlow<SplashComponent.State> = _state

    init {
        scope.launch {
            // if authToken is available, it means user is logged in
            val result = runCatching { getAuthToken() }
            _state.update { SplashComponent.State.Ready(result.isSuccess) }
        }
    }

    override fun onDestroy() {
        scope.cancel()
    }
}
