package social.androiddev.common.network.model

import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class AccountTests {
    // TODO: fix loading json from resources
    @Ignore
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        //val json: String = javaClass.classLoader.getResource("response_account_required.json").readText()
        val json: String = ""

        // when
        val account = Json.decodeFromString<Account>(json)

        // then

        assertEquals(expected = "23634", actual = account.id)
    }
}
