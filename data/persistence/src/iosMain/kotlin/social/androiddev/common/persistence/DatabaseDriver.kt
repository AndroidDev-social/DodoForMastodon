package social.androiddev.common.persistence

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual suspend fun provideDatabaseDriver(schema: SqlDriver.Schema): SqlDriver {
    return NativeSqliteDriver(schema = schema, name = "test.db")
}
