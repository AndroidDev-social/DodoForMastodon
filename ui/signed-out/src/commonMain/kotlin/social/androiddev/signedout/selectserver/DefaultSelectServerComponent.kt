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

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import social.androiddev.common.decompose.coroutineScope
import social.androiddev.common.web.WebAuth
import social.androiddev.common.web.WebOpenExtras
import social.androiddev.domain.authentication.model.ApplicationOAuthToken
import social.androiddev.domain.authentication.usecase.AuthenticateClient
import social.androiddev.domain.authentication.usecase.CreateAccessToken
import social.androiddev.domain.authentication.usecase.GetSelectedApplicationOAuthToken
import social.androiddev.signedout.util.encode
import kotlin.coroutines.CoroutineContext

class DefaultSelectServerComponent(
    private val componentContext: ComponentContext,
    mainContext: CoroutineContext,
    onAuthenticated: () -> Unit,
) : KoinComponent, SelectServerComponent, ComponentContext by componentContext {

    private val authenticateClient: AuthenticateClient by inject()
    private val getSelectedApplicationOAuthToken: GetSelectedApplicationOAuthToken by inject()
    private val createAccessToken: CreateAccessToken by inject()
    private val webAuth: WebAuth by inject()

    private val viewModel = instanceKeeper.getOrCreate {
        SelectServerViewModel(
            mainContext = mainContext,
            authenticateClient = authenticateClient,
            getSelectedApplicationOAuthToken = getSelectedApplicationOAuthToken,
            createAccessToken = createAccessToken,
            webAuth = webAuth,
        )
    }

    // The scope is automatically cancelled when the component is destroyed
    private val scope = coroutineScope(mainContext + SupervisorJob())

    override val state: StateFlow<SelectServerComponent.State> = viewModel.state

    init {
        scope.launch {
            viewModel.authenticated.collect { authenticated ->
                if (authenticated) {
                   onAuthenticated()
                }
            }
        }
    }

    override fun onServerSelected(server: String, extras: WebOpenExtras) {
        scope.launch {
            viewModel.validateAndOpenServerAuth(server, extras)
        }
    }

    override fun onAuthCanceled() {
        viewModel.cancelServerAuth()
    }
}

