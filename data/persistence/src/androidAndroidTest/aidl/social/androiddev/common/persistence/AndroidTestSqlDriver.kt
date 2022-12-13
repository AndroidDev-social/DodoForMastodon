package social.androiddev.common.persistence

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver

actual fun provideTestSqlDriver(schema: SqlDriver.Schema): SqlDriver {
    return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also { driver ->
        schema.create(driver = driver)
    }
}