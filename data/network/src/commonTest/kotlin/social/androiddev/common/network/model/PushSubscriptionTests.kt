package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class PushSubscriptionTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json = """
        {
            "id": "328183",
            "endpoint": "https://yourdomain.example/listener",
            "alerts":
            {
                "follow": false,
                "favourite": false,
                "reblog": false,
                "mention": true,
                "poll": false
            },
            "server_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
        """.trimIndent()

        // when
        val subscription = Json.decodeFromString<PushSubscription>(json)

        // then
        Truth.assertThat(subscription.id).isEqualTo("328183")
        Truth.assertThat(subscription.endpoint).isEqualTo("https://yourdomain.example/listener")
        Truth.assertThat(subscription.alerts.follow).isEqualTo(false)
        Truth.assertThat(subscription.alerts.favourite).isEqualTo(false)
        Truth.assertThat(subscription.alerts.reblog).isEqualTo(false)
        Truth.assertThat(subscription.alerts.mention).isEqualTo(true)
        Truth.assertThat(subscription.alerts.poll).isEqualTo(false)
        Truth.assertThat(subscription.serverKey).isEqualTo("BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M=")
    }
}
