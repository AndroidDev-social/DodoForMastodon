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
import kotlin.coroutines.CoroutineContext

class DefaultSignInComponent(
    mainContext: CoroutineContext,
    private val componentContext: ComponentContext,
    private val onSignInSucceedInternal: () -> Unit,
    private val onCloseClickedInternal: () -> Unit,
) : SignInComponent, ComponentContext by componentContext {

    // private val Get use case for sever
    private val viewModel = instanceKeeper.getOrCreate {
        SignInViewModel(
            mainContext = mainContext,
            server = "", // TODO get from pref
        )
    }

    override val state: StateFlow<SignInComponent.State> = viewModel.state

    override fun onSignInSucceed() {
        onSignInSucceedInternal()
    }

    override fun onCloseClicked() {
        onCloseClickedInternal()
    }
}
