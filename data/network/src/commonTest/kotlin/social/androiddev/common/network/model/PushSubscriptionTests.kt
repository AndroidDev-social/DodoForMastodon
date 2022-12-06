/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class PushSubscriptionTests {
    @Test
    fun `deserialize required fields should succeed`() {
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
