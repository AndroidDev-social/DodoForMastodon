package social.androiddev.common.network.model

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NotificationTests {
    // TODO: fix loading json from resources
    @Ignore
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        // val json: String = javaClass.classLoader.getResource("response_notification_required.json").readText()
        val json: String = ""

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
