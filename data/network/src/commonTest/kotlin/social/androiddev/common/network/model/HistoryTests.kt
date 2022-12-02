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

class HistoryTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "day": "1574553600",
            "uses": "200",
            "accounts": "31"
        }
        """.trimIndent()

        // when
        val history = Json.decodeFromString<History>(json)

        // then
        assertEquals(expected = "1574553600", actual = history.day)
        assertEquals(expected = "200", actual = history.uses)
        assertEquals(expected = "31", actual = history.accounts)
    }
}
