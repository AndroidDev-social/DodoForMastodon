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
import social.androiddev.domain.authentication.usecase.AuthenticateClient
import kotlin.coroutines.CoroutineContext

class DefaultSelectServerComponent(
    private val componentContext: ComponentContext,
    mainContext: CoroutineContext,
    private val launchOAuth: () -> Unit,
) : KoinComponent, SelectServerComponent, ComponentContext by componentContext {

    private val authenticateClient: AuthenticateClient by inject()

    private val viewModel = instanceKeeper.getOrCreate {
        SelectServerViewModel(
            mainContext = mainContext,
            authenticateClient = authenticateClient,
        )
    }

    // The scope is automatically cancelled when the component is destroyed
    private val scope = coroutineScope(mainContext + SupervisorJob())

    override val state: StateFlow<SelectServerComponent.State> = viewModel.state

    override fun onServerSelected(server: String) {
        scope.launch {
            val success = viewModel.validateServer(server)
            if (success) {
                launchOAuth()
            }
        }
    }
}
