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

class MarkerTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "home":
            {
                "last_read_id": "103194548672408537",
                "version": 462,
                "updated_at": "2019-11-24T19:39:39.337Z"
            },
            "notifications":
            {
                "last_read_id": "35050107",
                "version": 356,
                "updated_at": "2019-11-25T13:47:31.333Z"
            }
        }
        """.trimIndent()

        // when
        val marker = Json.decodeFromString<Marker>(json)

        // then
        val homeHash = MarkerHash(
            lastReadId = "103194548672408537",
            version = 462,
            updatedAt = "2019-11-24T19:39:39.337Z"
        )
        val usesHash = MarkerHash(
            lastReadId = "35050107",
            version = 356,
            updatedAt = "2019-11-25T13:47:31.333Z"
        )
        assertEquals(expected = homeHash, actual = marker.home)
        assertEquals(expected = usesHash, actual = marker.uses)
    }
}
