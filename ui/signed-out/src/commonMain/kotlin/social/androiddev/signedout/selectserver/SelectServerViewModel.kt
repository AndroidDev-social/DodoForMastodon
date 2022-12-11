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

        _state.update { it.copy(selectButtonEnabled = false) }

        val success = authenticateClient(
            domain = server,
            clientName = "Dodo",
            redirectURIs = REDIRECT_URIS,
            scopes = OAUTH_SCOPES,
            website = null,
        )

        return if (success) {
            true
        } else {
            _state.update { it.copy(selectButtonEnabled = true) }
            false
        }
    }

    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }


    companion object {
        private const val OAUTH_SCOPES = "read write follow push"
        private const val REDIRECT_URIS = "redirect_uris=urn:ietf:wg:oauth:2.0:oob"
    }

}