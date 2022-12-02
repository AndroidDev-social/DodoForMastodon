/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.navigation

import com.arkivanov.decompose.ComponentContext

class DefaultSelectServerComponent(
    private val componentContext: ComponentContext,
    private val launchOAuth: (server: String) -> Unit,
) : SelectServerComponent, ComponentContext by componentContext {

    override fun onServerSelected(server: String) {
        launchOAuth(server)
    }
}
