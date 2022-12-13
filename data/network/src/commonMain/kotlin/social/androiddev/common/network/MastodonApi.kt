/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network

import social.androiddev.common.network.model.Application
import social.androiddev.common.network.model.AvailableInstance
import social.androiddev.common.network.model.Instance
import social.androiddev.common.network.model.NewOauthApplication
import social.androiddev.common.network.model.Token

interface MastodonApi {

    /**
     * List available an registered Instances which can be joined by the user
     *
     * @return Instances committed to the Mastodon Server Covenant and listed at joinmastodon.org
     */
    suspend fun listInstances(): Result<List<AvailableInstance>>

    /**
     * Register client applications that can be used to obtain OAuth tokens.
     *
     * @see https://docs.joinmastodon.org/methods/apps/#create-an-application
     *
     * @param clientName A name for your application
     * @param redirectUris Where the user should be redirected after authorization.
     * To display the authorization code to the user instead of redirecting to a web page,
     * use urn:ietf:wg:oauth:2.0:oob in this parameter.
     * @param scopes Space separated list of scopes. If none is provided, defaults to read.
     * @param website An optional URL to the homepage of your app
     *
     * @return Application with `client_id` and `client_secret`
     */
    suspend fun createApplication(
        domain: String,
        clientName: String,
        redirectUris: String,
        scopes: String,
        website: String?,
    ): Result<NewOauthApplication>

    /**
     * Obtain an access token that will authenticate our requests as the authorized user.
     * @see https://docs.joinmastodon.org/client/authorized/#token
     */
    suspend fun createAccessToken(
        domain: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        grantType: String,
        code: String,
        scope: String
    ): Result<Token>

    /**
     * Confirm that the app’s OAuth2 credentials work.
     *
     * @see https://docs.joinmastodon.org/methods/apps/#verify_credentials
     *
     * @return an `application` if a valid token was provides in the authorization header
     */
    suspend fun verifyApplication(): Result<Application>

    /**
     * Fetch general information about the server
     *
     * @see https://docs.joinmastodon.org/methods/instance/
     *
     * @return an instance entity
     */
    suspend fun getInstance(domain: String? = null): Result<Instance>
}
