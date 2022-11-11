package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

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
        assertEquals(expected = "328183", actual = subscription.id)
        assertEquals(expected = "https://yourdomain.example/listener", actual = subscription.endpoint)
        assertEquals(expected = false, actual = subscription.alerts.follow)
        assertEquals(expected = false, actual = subscription.alerts.favourite)
        assertEquals(expected = false, actual = subscription.alerts.reblog)
        assertEquals(expected = true, actual = subscription.alerts.mention)
        assertEquals(expected = false, actual = subscription.alerts.poll)
        assertEquals(expected = "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M=", actual = subscription.serverKey)
    }
}
