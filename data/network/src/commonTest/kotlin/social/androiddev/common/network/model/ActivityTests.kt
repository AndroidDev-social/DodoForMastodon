package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ActivityTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json = """
        {
            "week": "1574640000",
            "statuses": "37125",
            "logins": "14239",
            "registrations": "542"
        }
        """.trimIndent()

        // when
        val activity = Json.decodeFromString<Activity>(json)

        // then
        Truth.assertThat(activity.week).isEqualTo("1574640000")
        Truth.assertThat(activity.statuses).isEqualTo("37125")
        Truth.assertThat(activity.logins).isEqualTo("14239")
        Truth.assertThat(activity.registrations).isEqualTo("542")
    }
}
