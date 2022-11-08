package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class NotificationTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json: String = javaClass.classLoader.getResource("response_notification_required.json").readText()

        // when
        val notification = Json.decodeFromString<Notification>(json)

        // then
        Truth.assertThat(notification).isNotNull()
        Truth.assertThat(notification.id).isEqualTo("34975861")
        Truth.assertThat(notification.type).isEqualTo(NotificationType.mention)
        Truth.assertThat(notification.createdAt).isEqualTo("2019-11-23T07:49:02.064Z")
        Truth.assertThat(notification.account).isNotNull()
        Truth.assertThat(notification.account.id).isEqualTo("23634")
        Truth.assertThat(notification.status).isNotNull()
        Truth.assertThat(notification.status.id).isEqualTo("103270115826048975")
    }
}
