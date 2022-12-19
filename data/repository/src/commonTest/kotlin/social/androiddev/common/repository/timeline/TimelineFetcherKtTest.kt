package social.androiddev.common.repository.timeline

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.mobilenativefoundation.store.store5.FetcherResult
import social.androiddev.common.network.model.Privacy
import social.androiddev.common.network.model.Status
import social.androiddev.common.repository.timeline.fixtures.fakeApi
import social.androiddev.common.repository.timeline.fixtures.fakeStorage
import social.androiddev.common.timeline.StatusDB
import social.androiddev.domain.timeline.FeedType
import kotlin.test.Test
import kotlin.test.assertTrue


class TimelineFetcherKtTest {
    @Test
    fun timelineFetcher() = runTest {
        val fetcher = fakeApi.timelineFetcher(fakeStorage)
        val result = fetcher.invoke(FeedType.Home)
        val value: FetcherResult<List<StatusDB>> = result.first()
        assertTrue { value is FetcherResult.Data<*> }

        val expected = Status(
            "",
            "",
            "",
            null,
            "",
            Privacy.direct,
            false,
            "",
        )

        val list: List<StatusDB> = (value as FetcherResult.Data).value
        assertTrue { list.size == 1 }
        assertTrue { list[0] == expected.statusDB() }
    }
}


