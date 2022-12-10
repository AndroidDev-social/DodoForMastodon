/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.navigation

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import social.androiddev.domain.authentication.usecase.AuthenticateClient

class DefaultSelectServerComponent(
    private val componentContext: ComponentContext,
    private val launchOAuth: (server: String) -> Unit,
) : KoinComponent, SelectServerComponent, ComponentContext by componentContext {

    private val authenticClient: AuthenticateClient by inject()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val _state = MutableStateFlow(SelectServerComponent.State())
    override val state: StateFlow<SelectServerComponent.State> = _state

    override fun onServerSelected(server: String) {
        _state.update { it.copy(selectButtonEnabled = false) }
        scope.launch {
            val success = authenticClient(
                domain = server,
                clientName = "Dodo",
                redirectURIs = REDIRECT_URIS,
                scopes = OAUTH_SCOPES,
                website = null,
            )

            if (success) {
                launchOAuth(server)
            } else {
                _state.update { it.copy(selectButtonEnabled = true) }
            }
        }
    }

    companion object {
        private const val OAUTH_SCOPES = "read write follow push"
        private const val REDIRECT_URIS = "redirect_uris=urn:ietf:wg:oauth:2.0:oob"
    }
}
