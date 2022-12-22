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
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import social.androiddev.common.web.WebAuth
import social.androiddev.common.web.WebOpenExtras
import social.androiddev.domain.authentication.model.ApplicationOAuthToken
import social.androiddev.domain.authentication.usecase.AuthenticateClient
import social.androiddev.domain.authentication.usecase.CreateAccessToken
import social.androiddev.domain.authentication.usecase.GetSelectedApplicationOAuthToken
import social.androiddev.signedout.util.encode
import kotlin.coroutines.CoroutineContext

internal class SelectServerViewModel(
    mainContext: CoroutineContext,
    private val authenticateClient: AuthenticateClient,
    private val getSelectedApplicationOAuthToken: GetSelectedApplicationOAuthToken,
    private val createAccessToken: CreateAccessToken,
    private val webAuth: WebAuth,
) : InstanceKeeper.Instance {

    // The scope survives Android configuration changes
    private val scope = CoroutineScope(mainContext + SupervisorJob())

    private val _state = MutableStateFlow(SelectServerComponent.State())
    val state: StateFlow<SelectServerComponent.State> = _state.asStateFlow()

    private val _authenticated = MutableStateFlow(false)
    val authenticated: StateFlow<Boolean> = _authenticated

    private val redirectUri = scope.async { webAuth.start() }

    init {
        scope.launch {
            webAuth.state.collect { state ->
                when (state) {
                    is WebAuth.State.Success -> {
                        val success = createAccessToken(
                            authCode = state.code,
                            server = _state.value.server
                        )
                        if (success) {
                            _authenticated.value = true
                        } else {
                            _state.update {
                                it.copy(
                                    loading = false,
                                    error = "An error occurred."
                                )
                            }
                        }
                    }

                    is WebAuth.State.Error -> {
                        _state.update {
                            it.copy(
                                loading = false,
                                error = state.error ?: "Something is Wrong!"
                            )
                        }
                    }
                }
            }
        }
    }

    suspend fun validateAndOpenServerAuth(server: String, extras: WebOpenExtras) {
        // TODO Sanitize and format the user entered server

        _state.update {
            it.copy(
                server = server,
                loading = true,
                error = null
            )
        }

        val success = authenticateClient(
            domain = server,
            clientName = "Dodo",
            redirectURIs = redirectUri.await(),
            scopes = OAUTH_SCOPES,
            website = "https://$server",
        )

        if (success) {
            val token = getSelectedApplicationOAuthToken()
            webAuth.open(createOAuthAuthorizeUrl(token), extras)
        } else {
            _state.update { it.copy(loading = false) }
        }
    }

    fun cancelServerAuth() {
        _state.update {
            it.copy(loading = false)
        }
    }

    override fun onDestroy() {
        scope.cancel() // Cancel the scope when the instance is destroyed
    }

    companion object {
        private const val OAUTH_SCOPES = "read write follow push"
    }
}

private fun createOAuthAuthorizeUrl(token: ApplicationOAuthToken): String {
    return buildString {
        append("https://${token.server}")
        append("/oauth/authorize?client_id=${token.clientId}")
        append("&scope=${"read write follow push".encode()}")
        append("&redirect_uri=${token.redirectUri.encode()}")
        append("&response_type=code")
    }
}
