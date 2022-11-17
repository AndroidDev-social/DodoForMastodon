/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.persistence.authentication

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import social.androiddev.common.persistence.AuthenticationDatabase
import social.androiddev.common.persistence.provideTestSqlDriver
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
internal class AuthenticationDatabaseTests {

    @Test
    fun `insert application into clean database should contain one entry`() = runTest {
        // given
        val driver = provideTestSqlDriver(AuthenticationDatabase.Schema)
        val database = AuthenticationDatabase(driver = driver)

        // when
        database.applicationQueries.deleteAll()
        database.applicationQueries.insertApplication(
            instance = "androiddev.social",
            client_id = "test_client_id",
            client_secret = "test_client_secret"
        )

        // then
        val list = database.applicationQueries.selectAll().executeAsList()
        assertEquals(expected = 1, list.size)
    }
}
