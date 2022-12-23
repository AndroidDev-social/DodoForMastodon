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
package social.androiddev.common.persistence

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual fun provideTestSqlDriver(schema: SqlDriver.Schema): SqlDriver {
    return JdbcSqliteDriver(url = JdbcSqliteDriver.IN_MEMORY).also { driver ->
        schema.create(driver = driver)
    }
}
