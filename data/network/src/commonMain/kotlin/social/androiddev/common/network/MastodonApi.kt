/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network

import social.androiddev.common.network.model.Application
import social.androiddev.common.network.model.Instance

interface MastodonApi {
    /**
     * Fetch information about the server
     *
     * @see https://docs.joinmastodon.org/methods/instance/
     */
    suspend fun getInstance(domain: String? = null): Result<Instance>

    /**
     * Register client applications that can be used to obtain OAuth tokens.
     *
     * @see https://docs.joinmastodon.org/methods/apps/#create-an-application
     *
     * [clientName] A name for your application
     * [redirectUris] Where the user should be redirected after authorization.
     * To display the authorization code to the user instead of redirecting to a web page,
     * use urn:ietf:wg:oauth:2.0:oob in this parameter.
     * [scopes] Space separated list of scopes. If none is provided, defaults to read.
     * [website] An optional URL to the homepage of your app
     */
    suspend fun createApplication(
        clientName: String,
        redirectUris: String,
        scopes: String = "read",
        website: String?,
    ): Result<Application>
}
