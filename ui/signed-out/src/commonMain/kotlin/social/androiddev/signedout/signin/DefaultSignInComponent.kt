/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import social.androiddev.domain.authentication.usecase.CreateAccessToken
import social.androiddev.domain.authentication.usecase.GetSelectedApplicationOAuthToken
import kotlin.coroutines.CoroutineContext

class DefaultSignInComponent(
    mainContext: CoroutineContext,
    private val componentContext: ComponentContext,
    private val onSignInSucceedInternal: () -> Unit,
    private val onCloseClickedInternal: () -> Unit,
) : SignInComponent, KoinComponent, ComponentContext by componentContext {

    private val getSelectedApplicationOAuthToken: GetSelectedApplicationOAuthToken by inject()
    private val createAccessToken: CreateAccessToken by inject()

    private val viewModel = instanceKeeper.getOrCreate {
        SignInViewModel(
            mainContext = mainContext,
            getSelectedApplicationOAuthToken = getSelectedApplicationOAuthToken,
            createAccessToken = createAccessToken,
            onSignedIn = onSignInSucceedInternal,
        )
    }

    override val state: StateFlow<SignInComponent.State> = viewModel.state

    override fun onCloseClicked() {
        onCloseClickedInternal()
    }

    override fun onErrorFromOAuth(error: String) {
        viewModel.onErrorFromOAuth(error)
    }
    override fun shouldCancelLoadingUrl(url: String): Boolean {
        return viewModel.shouldCancelLoadingUrl(url)
    }
}
