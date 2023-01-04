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
package social.androiddev.common.repository.timeline

import app.cash.turbine.test
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import social.androiddev.common.repository.AuthenticationRepositoryImpl
import social.androiddev.common.repository.timeline.fixtures.FakeAuthDatabase
import social.androiddev.common.repository.timeline.fixtures.FakeAuthStorage
import social.androiddev.common.repository.timeline.fixtures.fakeApi
import social.androiddev.domain.authentication.repository.AuthenticationRepository
import kotlin.coroutines.CoroutineContext
import kotlin.test.Test
import kotlin.test.assertEquals

private fun createRepo(
    ctx: CoroutineContext,
    serversFlow: Flow<List<String>>,
): AuthenticationRepository = AuthenticationRepositoryImpl(
    mastodonApi = fakeApi,
    database = FakeAuthDatabase(),
    settings = FakeAuthStorage(serversFlow),
    ioCoroutineContext = ctx
)

class AuthenticationRepositoryTest {

    @Test
    fun notifiesAboutAuthServerChange() = runTest {
        val serverFlow = MutableStateFlow<List<String>>(listOf())
        val repo = createRepo(this.coroutineContext, serverFlow)

        repo.getIsAccessTokenPresent().test {
            skipItems(1) // skip initial
            serverFlow.emit(listOf("server1", "server2"))
            assertEquals(true, awaitItem())
            serverFlow.emit(listOf("server3"))
            assertEquals(true, awaitItem())
            serverFlow.emit(listOf())
            assertEquals(false, awaitItem())
        }
    }
}
