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
    @Test
    fun sucessTest(): TestResult {
        return runTest {
            val testRepo = RealHomeTimelineRepository(fakeSuccessStore)
            val result = testRepo.read(FeedType.Home).first()
            assertTrue { result is StoreResponse.Data }
            assertTrue { result.requireData().first() == fakeLocalStatus }

        }
    }

    @Test
    fun failureTest(): TestResult {
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
