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
        return "https://$server/oauth/authorize?client_id=$CLIENT_ID&scope=$OAUTH_SCOPES&redirect_uri=https://dodomastodon.com&response_type=code"
    }

    fun resolveSignInStatusFromUrl(
        url: String,
        onSignedIn: () -> Unit,
        onFailed: (error: String) -> Unit
    ) {
        val uri = URI(url)
        if (isOauthUrl(uri)) {
            // TODO enhance the impl to extract uri query params
            // see Uri.getQueryParameter in Android sdk
            if (uri.query.contains("error")) {
                val error = uri.query.replace("error=", "")
                onFailed(error)
            } else {
                // TODO save code in the user session
                val code = uri.query.replace("code=", "")
                onSignedIn()
            }
        }
    }

    private fun isOauthUrl(uri: URI) =
        uri.scheme == REDIRECT_URL_SCHEME && uri.host == REDIRECT_URL_HOST

    companion object {
        // TODO Use the right redirect url host and scheme get them from a secure location
        private const val REDIRECT_URL_HOST = "dodomastodon.com"
        private const val REDIRECT_URL_SCHEME = "https"
        private const val CLIENT_ID = "TODO"
        private const val OAUTH_SCOPES = "read+write+follow+push"
    }
}
