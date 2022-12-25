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

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class ComposeTootViewModel(
    mainContext: CoroutineContext,
    private val onTootSucceed: () -> Unit
) : InstanceKeeper.Instance {

    // The scope survives Android configuration changes
    private val scope = CoroutineScope(mainContext + SupervisorJob())

    private val _state = MutableStateFlow(createInitialState())

    private fun createInitialState(): ComposeTootState {
        return ComposeTootState(
            content = Content(toot = "", null),
            currentUser = CurrentUser(
                displayName = "Mohammed",
                avatarUrl = "https://cdn.masto.host/androiddevsocial/accounts/avatars/109/" +
                    "412/078/242/933/246/original/dc1c7b288e98c67e.jpeg"
            ),
            tootTextCounter = "$TOOT_LENGTH",
            postTootEnabled = false
        )
    }

    val state: StateFlow<ComposeTootState> = _state.asStateFlow()

    override fun onDestroy() {
        scope.cancel()
    }

    fun onActionClicked(action: Action) {
        _state.update {
            // TODO implement the right logic
            if (action.needActionReset(it.selectedAction)) {
                it.copy(selectedAction = null)
            } else {
                it.copy(selectedAction = action)
            }
        }
    }

    private fun Action.needActionReset(currentAction: Action?): Boolean {
        return this == currentAction ||
            this == Action.AddHashtag ||
            this == Action.AddMention ||
            this == Action.ChooseLanguage
    }

    fun onTootContentChange(text: String) {
        val tapedTextLength = text.length
        if (tapedTextLength <= 500) {
            _state.update {
                val currentContent = it.content
                it.copy(
                    content = Content(toot = text, warning = currentContent.warning),
                    tootTextCounter = "${500 - tapedTextLength}",
                    postTootEnabled = text.isNotBlank()
                )
            }
        }
    }

    fun onPostClicked() {
        // TODO("Not yet implemented")
        scope.launch {
            _state.update {
                it.copy(postTootEnabled = false)
            }
            delay(1000)
            onTootSucceed()
            _state.update {
                it.copy(postTootEnabled = true)
            }
        }
    }

    companion object {
        private const val TOOT_LENGTH = 500
    }
}
