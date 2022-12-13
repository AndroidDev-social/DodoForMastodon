/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.selectserver

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import social.androiddev.domain.authentication.usecase.AuthenticateClient
import kotlin.coroutines.CoroutineContext

internal class SelectServerViewModel(
    mainContext: CoroutineContext,
    private val authenticateClient: AuthenticateClient
) : InstanceKeeper.Instance {

    // The scope survives Android configuration changes
    private val scope = CoroutineScope(mainContext + SupervisorJob())

    private val _state = MutableStateFlow(SelectServerComponent.State())
    val state: StateFlow<SelectServerComponent.State> = _state.asStateFlow()

    suspend fun validateServer(server: String): Boolean {

        // TODO Sanitize and format the user entered server

        _state.update { it.copy(selectButtonEnabled = false) }

        val success = authenticateClient(
            domain = server,
            clientName = "Dodo",
            redirectURIs = "$redirectScheme://$server/",
            scopes = OAUTH_SCOPES,
            website = "https://$server",
        )
        _state.update { it.copy(selectButtonEnabled = true) }
        return success
    }

    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }

    companion object {
        private const val OAUTH_SCOPES = "read write follow push"
    }
}
