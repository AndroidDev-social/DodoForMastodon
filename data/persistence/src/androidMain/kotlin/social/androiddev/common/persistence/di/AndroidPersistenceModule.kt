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

import com.russhwolf.settings.SharedPreferencesSettings
import com.squareup.sqldelight.android.AndroidSqliteDriver
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module
import social.androiddev.common.persistence.AuthenticationDatabase
import social.androiddev.common.persistence.localstorage.DodoAuthStorage
import social.androiddev.common.persistence.localstorage.DodoAuthStorageImpl
import social.androiddev.common.timeline.TimelineDatabase

/**
 * Koin DI module for all android specific persistence dependencies
 */
actual val persistenceModule: Module = module {
    single<DodoAuthStorage> {
        DodoAuthStorageImpl(
            settings = SharedPreferencesSettings
                .Factory(
                    context = get(),
                )
                .create(AUTH_SETTINGS_NAME),
            json = Json {
                ignoreUnknownKeys = true
                encodeDefaults = false
            }
        )
    }

    single {
        val driver = AndroidSqliteDriver(
            schema = AuthenticationDatabase.Schema,
            context = get(),
            name = AUTH_DB_NAME,
        )
        AuthenticationDatabase(driver)
    }

    single {
        val driver = AndroidSqliteDriver(
            schema = TimelineDatabase.Schema,
            context = get(),
            name = FEED_DB_NAME,
        )
        TimelineDatabase(driver)
    }
}
