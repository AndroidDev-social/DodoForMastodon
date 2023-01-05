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
package social.androiddev.root.root

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import social.androiddev.domain.authentication.model.AuthStatus
import social.androiddev.domain.authentication.usecase.GetAuthStatus
import kotlin.coroutines.CoroutineContext

class RootComponentViewModel(coroutineContext: CoroutineContext, getAuthStatus: GetAuthStatus) :
    InstanceKeeper.Instance {
    private val viewModelScope = CoroutineScope(coroutineContext + SupervisorJob())

    private val _authState = MutableStateFlow<RootComponent.UiAuthStatus>(RootComponent.UiAuthStatus.Unknown)
    val authState = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            getAuthStatus().collect { currentStatus ->
                _authState.value = currentStatus.toUiAuthStatus()
            }
        }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}

private fun AuthStatus.toUiAuthStatus(): RootComponent.UiAuthStatus {
    return when (this) {
        is AuthStatus.Authorized -> RootComponent.UiAuthStatus.Authorized
        is AuthStatus.Unauthorized -> RootComponent.UiAuthStatus.Unauthorized
    }
}
