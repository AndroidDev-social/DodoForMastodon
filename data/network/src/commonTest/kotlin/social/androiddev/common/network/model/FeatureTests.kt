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

class FeatureTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "id": "627",
            "name": "nowplaying",
            "url": "",
            "statuses_count": 36,
            "last_status_at": "2019-11-15T07:14:43.524Z"
        }
        """.trimIndent()

        // when
        val featuredTag = Json.decodeFromString<FeaturedTag>(json)

        // then
        assertEquals(expected = "627", actual = featuredTag.id)
        assertEquals(expected = "nowplaying", actual = featuredTag.name)
        assertEquals(expected = 36, actual = featuredTag.statusesCount)
        assertEquals(expected = "2019-11-15T07:14:43.524Z", actual = featuredTag.lastStatusAt)
    }
}
