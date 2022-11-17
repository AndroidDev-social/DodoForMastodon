package social.androiddev.common.persistence

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual suspend fun provideDatabaseDriver(schema: SqlDriver.Schema): SqlDriver {
    return JdbcSqliteDriver(url = JdbcSqliteDriver.IN_MEMORY).also { driver ->
        schema.create(driver = driver)
    }
}
