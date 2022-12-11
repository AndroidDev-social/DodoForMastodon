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