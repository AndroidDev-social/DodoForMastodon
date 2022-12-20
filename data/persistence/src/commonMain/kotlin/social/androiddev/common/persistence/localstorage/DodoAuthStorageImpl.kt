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
package social.androiddev.common.persistence.localstorage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

internal class DodoAuthStorageImpl(
    private val settings: Settings,
    private val json: Json
) : DodoAuthStorage {

    override var currentDomain: String?
        get() = settings[KEY_DOMAIN_CACHE]
        set(value) {
            settings[KEY_DOMAIN_CACHE] = value
        }

    /**
     * The user can set up multiple accounts on their device. So we
     * key the AccessToken by the unique server/domain
     */
    private var diskCache: Map<String, AccessToken>
        get() {
            return settings
                .getStringOrNull(KEY_ACCESS_TOKENS_CACHE)
                ?.let { str ->
                    json.decodeFromString(ListSerializer(AccessToken.serializer()), str)
                        .associateBy { it.server }
                }
                ?: mutableMapOf()
        }
        set(value) {
            val list = value.map { it.value }
            settings[KEY_ACCESS_TOKENS_CACHE] =
                json.encodeToString(ListSerializer(AccessToken.serializer()), list)
        }

    private val memCache: MutableMap<String, AccessToken> by lazy { diskCache.toMutableMap() }

    override fun getAccessToken(server: String): String? = memCache[server]?.token

    override suspend fun saveAccessToken(server: String, token: String) {
        memCache[server] = AccessToken(token = token, server = server)
        diskCache = memCache
    }

    private companion object {
        private const val KEY_DOMAIN_CACHE = "key_domain_cache"
        private const val KEY_ACCESS_TOKENS_CACHE = "key_access_tokens_cache"
    }

    @Serializable
    private data class AccessToken(
        val token: String,
        val server: String,
    )
}
