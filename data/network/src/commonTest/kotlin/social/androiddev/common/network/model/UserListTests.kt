/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class UserListTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "id": "12249",
            "title": "Friends",
            "replies_policy": "list"
        }
        """.trimIndent()

        // when
        val userList = Json.decodeFromString<UserList>(json)

        // then
        assertEquals(expected = "12249", actual = userList.id)
        assertEquals(expected = "Friends", actual = userList.title)
        assertEquals(expected = RepliesPolicy.list, actual = userList.repliesPolicy)
    }
}
