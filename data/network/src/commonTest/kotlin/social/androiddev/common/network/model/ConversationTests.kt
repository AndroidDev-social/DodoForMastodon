package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.goncalossilva.resources.Resource
import kotlin.test.Test
import kotlin.test.assertNotNull

class ConversationTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = Resource("src/commonTest/resources/response_conversation_required.json").readText()

        // when
        val conversation = Json.decodeFromString<Conversation>(json)

        // then
        assertNotNull(actual = conversation)
        assertEquals(expected = "418450", actual = conversation.id)
        assertNotNull(actual = conversation.accounts)
        assertEquals(expected = 1, actual = conversation.accounts.size)
        assertEquals(expected = true, actual = conversation.unread)
        assertNotNull(actual = conversation.lastStatus)
        assertEquals(expected = "103270115826048975", actual = conversation.lastStatus.id)
    }
}
