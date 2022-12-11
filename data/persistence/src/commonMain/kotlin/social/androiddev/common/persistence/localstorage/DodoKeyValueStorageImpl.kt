/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.persistence.localstorage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.serialization.json.Json

internal class DodoKeyValueStorageImpl(
    private val settings: Settings,
    private val json: Json
) : DodoKeyValueStorage {

    override var currentDomain: String?
        get() = settings[KEY_DOMAIN_CACHE]
        set(value) {
            settings[KEY_DOMAIN_CACHE] = value
        }

    private companion object {
        private const val KEY_DOMAIN_CACHE = "key_domain_cache"
    }
}
