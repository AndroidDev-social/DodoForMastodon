package social.androiddev.common.persistence.di

import com.russhwolf.settings.NSUserDefaultsSettings
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import social.androiddev.common.persistence.AuthenticationDatabase
import social.androiddev.common.persistence.localstorage.DodoKeyValueStorage
import social.androiddev.common.persistence.localstorage.DodoKeyValueStorageImpl
import social.androiddev.common.persistence.provideDatabaseDriver

actual val persistenceModule: Module = module {
    single<DodoKeyValueStorage> {
        DodoKeyValueStorageImpl(
            settings = NSUserDefaultsSettings
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
