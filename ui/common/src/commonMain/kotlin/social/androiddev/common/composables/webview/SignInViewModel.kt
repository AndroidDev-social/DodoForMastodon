/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.composables.webview

import java.net.URI

// TODO USE DI
// Add tests
class SignInViewModel(
    private val server: String
) {

    fun getSignInUrl(): String {
        // TODO
        //  Use the right client id and redirect url and get them from a secure location
        //  build url with an url builder my be using Ktor !!
        return "https://$server/oauth/authorize?client_id=$CLIENT_ID&redirect_uri=https://dodomastodon.com&response_type=code&scope=$OAUTH_SCOPES"
    }

    fun resolveSignInStatusFromUrl(
        url: String,
        onSingedIn: () -> Unit,
        onFailed: (error: String) -> Unit
    ) {
        val uri = URI(url)
        if (isOauthUrl(uri)) {
            if (uri.query.contains("error")) {
                val error = uri.query.replace("error=", "")
                println("error =$error")
                onFailed(error)
            } else {
                val code = uri.query.replace("code=", "")
                // TODO save code in the user session
                println("code =$code")
                onSingedIn()
            }
        }
    }

    private fun isOauthUrl(uri: URI) =
        uri.scheme == REDIRECT_URL_SCHEME && uri.host == REDIRECT_URL_HOST

    companion object {
        const val REDIRECT_URL_HOST = "dodomastodon.com"
        const val REDIRECT_URL_SCHEME = "https"
        const val CLIENT_ID = "TODO"
        private const val OAUTH_SCOPES = "read"
    }
}
