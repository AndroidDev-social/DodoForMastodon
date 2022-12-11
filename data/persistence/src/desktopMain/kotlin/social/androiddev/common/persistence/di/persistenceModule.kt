/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.persistence.di

import com.russhwolf.settings.PreferencesSettings
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import social.androiddev.common.persistence.AuthenticationDatabase
import social.androiddev.common.persistence.localstorage.DodoAuthStorage
import social.androiddev.common.persistence.localstorage.DodoAuthStorageImpl
import social.androiddev.common.persistence.provideDatabaseDriver

/**
 * Koin module containing all koin/bean definitions for
 * persistent storage related classes
 */
actual val persistenceModule: Module = module {
    single<DodoAuthStorage> {
        DodoAuthStorageImpl(
            settings = PreferencesSettings
                .Factory()
                .create("DodoAuthSettings"),
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
