package social.androiddev.common.persistence

import com.squareup.sqldelight.db.SqlDriver

expect suspend fun provideDatabaseDriver(schema: SqlDriver.Schema): SqlDriver
