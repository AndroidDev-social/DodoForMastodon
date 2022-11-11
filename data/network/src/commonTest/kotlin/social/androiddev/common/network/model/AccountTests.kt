package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.goncalossilva.resources.Resource
import kotlin.test.Test

class AccountTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json: String = Resource("src/commonTest/resources/response_account_required.json").readText()

        // when
        val account = Json.decodeFromString<Account>(json)

        // then

        assertEquals(expected = "23634", actual = account.id)
    }
}
