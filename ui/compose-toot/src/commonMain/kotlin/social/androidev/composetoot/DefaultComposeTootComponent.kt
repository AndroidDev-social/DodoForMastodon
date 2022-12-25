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
package social.androidev.composetoot

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.flow.StateFlow
import kotlin.coroutines.CoroutineContext

class DefaultComposeTootComponent(
    mainContext: CoroutineContext,
    private val componentContext: ComponentContext,
    private val onCloseClickedInternal: () -> Unit,
) : ComposeTootComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate {
        ComposeTootViewModel(
            mainContext = mainContext,
            onTootSucceed = onCloseClickedInternal
        )
    }
    override val state: StateFlow<ComposeTootState> = viewModel.state

    override fun onCloseClicked() {
        onCloseClickedInternal()
    }

    override fun onTootContentChange(text: String) {
        viewModel.onTootContentChange(text)
    }

    override fun onPostClicked() {
        viewModel.onPostClicked()
    }

    override fun onActionClicked(action: Action) {
        viewModel.onActionClicked(action)
    }
}
