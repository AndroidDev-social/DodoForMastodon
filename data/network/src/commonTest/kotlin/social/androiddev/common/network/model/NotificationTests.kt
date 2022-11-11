package social.androiddev.common.network.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.goncalossilva.resources.Resource

class NotificationTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json: String = Resource("src/commonTest/resources/response_notification_required.json").readText()

        // when
        val notification = Json.decodeFromString<Notification>(json)

        // then
        assertNotNull(actual = notification)
        assertEquals(expected = "34975861", actual = notification.id)
        assertEquals(expected = NotificationType.mention, actual = notification.type)
        assertEquals(expected = "2019-11-23T07:49:02.064Z", actual = notification.createdAt)
        assertNotNull(actual = notification.account)
        assertEquals(expected = "23634", actual = notification.account.id)
        assertNotNull(actual = notification.status)
        assertEquals(expected = "103270115826048975", actual = notification.status.id)
    }
}
