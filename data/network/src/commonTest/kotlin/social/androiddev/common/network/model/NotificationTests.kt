/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import social.androiddev.common.readBinaryResource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class NotificationTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val byteArray: ByteArray = readBinaryResource("src/commonTest/resources/response_notification_required.json")
        val json: String = byteArray.decodeToString()

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
