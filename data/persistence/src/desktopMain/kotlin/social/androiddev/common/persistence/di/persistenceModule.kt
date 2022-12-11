package social.androiddev.common.persistence.di

import com.russhwolf.settings.PreferencesSettings
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import social.androiddev.common.persistence.AuthenticationDatabase
import social.androiddev.common.persistence.localstorage.DodoKeyValueStorage
import social.androiddev.common.persistence.localstorage.DodoKeyValueStorageImpl
import social.androiddev.common.persistence.provideDatabaseDriver

/**
 * Koin module containing all koin/bean definitions for
 * persistent storage related classes
 */
actual val persistenceModule: Module = module {
    single<DodoKeyValueStorage> {
        DodoKeyValueStorageImpl(
            settings = PreferencesSettings
                .Factory()
                .create("DodoSettings"),
            json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = false
            }
        )
    }

    single {
        val driver = provideDatabaseDriver(AuthenticationDatabase.Schema)
        AuthenticationDatabase(driver)
    }
}