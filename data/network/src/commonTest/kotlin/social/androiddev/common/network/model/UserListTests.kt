package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class UserListTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
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
        Truth.assertThat(userList.id).isEqualTo("12249")
        Truth.assertThat(userList.title).isEqualTo("Friends")
        Truth.assertThat(userList.repliesPolicy).isEqualTo(RepliesPolicy.list)
    }
}
