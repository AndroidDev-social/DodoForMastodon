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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import social.androiddev.domain.authentication.model.AuthStatus
import social.androiddev.domain.authentication.usecase.GetAuthStatus
import kotlin.coroutines.CoroutineContext

class RootComponentViewModel(
    coroutineContext: CoroutineContext,
    getAuthStatus: GetAuthStatus,
) : InstanceKeeper.Instance {
    private val viewModelScope = CoroutineScope(coroutineContext + SupervisorJob())

    val authState =
        getAuthStatus()
            .map { it.toUiAuthStatus() }
            .stateIn(viewModelScope, SharingStarted.Lazily, UiAuthStatus.Loading)

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}

private fun AuthStatus.toUiAuthStatus(): UiAuthStatus {
    return when (this) {
        is AuthStatus.Authorized -> UiAuthStatus.Authorized
        is AuthStatus.Unauthorized -> UiAuthStatus.Unauthorized
    }
}
