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

class FilterTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "id": "8449",
            "phrase": "test",
            "context":
            [
                "home",
                "notifications",
                "public",
                "thread"
            ],
            "whole_word": false,
            "expires_at": "2019-11-26T09:08:06.254Z",
            "irreversible": true
        }
        """.trimIndent()

        // when
        val filter = Json.decodeFromString<Filter>(json)

        // then
        assertEquals(expected = "8449", actual = filter.id)
        assertEquals(expected = "test", actual = filter.phrase)
        assertEquals(
            expected = listOf(
                FilterContext.home, FilterContext.notifications, FilterContext.public, FilterContext.thread
            ),
            actual = filter.context
        )
        assertEquals(expected = false, actual = filter.wholeWord)
        assertEquals(expected = "2019-11-26T09:08:06.254Z", actual = filter.expiresAt)
        assertEquals(expected = true, actual = filter.irreversible)
    }
}
