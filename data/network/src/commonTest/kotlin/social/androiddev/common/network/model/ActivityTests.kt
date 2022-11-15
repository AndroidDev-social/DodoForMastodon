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
import kotlin.test.Test
import kotlin.test.assertEquals

class ActivityTests {
    @Test
    fun `deserialize required fields should succeed`() {
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
        assertEquals(expected = "1574640000", actual = activity.week)
        assertEquals(expected = "37125", actual = activity.statuses)
        assertEquals(expected = "14239", actual = activity.logins)
        assertEquals(expected = "542", actual = activity.registrations)
    }
}
