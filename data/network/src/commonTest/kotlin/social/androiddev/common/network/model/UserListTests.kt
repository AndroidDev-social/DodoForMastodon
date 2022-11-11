package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

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
