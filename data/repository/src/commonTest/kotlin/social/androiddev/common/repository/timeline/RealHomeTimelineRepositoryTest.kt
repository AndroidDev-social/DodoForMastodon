/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.repository.timeline

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.store.store5.ExperimentalStoreApi
import org.mobilenativefoundation.store.store5.ResponseOrigin
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreRequest
import org.mobilenativefoundation.store.store5.StoreResponse
import social.androiddev.common.repository.timeline.fixtures.failureResponse
import social.androiddev.common.repository.timeline.fixtures.fakeLocalStatus
import social.androiddev.domain.timeline.FeedType
import social.androiddev.domain.timeline.model.StatusLocal
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class RealHomeTimelineRepositoryTest {
    @Test fun successTest(): TestResult {
        return runTest {
            val testRepo = RealHomeTimelineRepository(fakeSuccessStore)
            val result = testRepo.read(FeedType.Home).first()
            assertTrue { result is StoreResponse.Data }
            assertTrue { result.requireData().first() == fakeLocalStatus }
        }
    }

    @Test fun failureTest(): TestResult {
        return runTest {
            val testRepo = RealHomeTimelineRepository(fakeFailureStore)
            val result = testRepo.read(FeedType.Home).first()
            assertTrue { result is StoreResponse.Error.Message }
            assertTrue { result.errorMessageOrNull() == failureResponse.message }
        }
    }
}
val fakeSuccessStore = object : Store<FeedType, List<StatusLocal>> {
    override suspend fun clear(key: FeedType) {
        TODO("Not yet implemented")
    }

    @ExperimentalStoreApi
    override suspend fun clearAll() {
        TODO("Not yet implemented")
    }

    override fun stream(request: StoreRequest<FeedType>): Flow<StoreResponse<List<StatusLocal>>> {
        return when (request.key.type) {
            FeedType.Home.type -> {
                flow {
                    emit(
                        StoreResponse.Data(
                            listOf(fakeLocalStatus),
                            ResponseOrigin.Cache
                        )
                    )
                }
            }

            else -> {
                fail("wrong response")
            }
        }
    }
}

val fakeFailureStore = object : Store<FeedType, List<StatusLocal>> {
    override suspend fun clear(key: FeedType) {
        TODO("Not yet implemented")
    }

    @ExperimentalStoreApi
    override suspend fun clearAll() {
        TODO("Not yet implemented")
    }

    override fun stream(request: StoreRequest<FeedType>): Flow<StoreResponse<List<StatusLocal>>> {
        return when (request.key.type) {
            FeedType.Home.type -> {
                flow {
                    emit(failureResponse)
                }
            }

            else -> {
                fail("wrong response")
            }
        }
    }
}
