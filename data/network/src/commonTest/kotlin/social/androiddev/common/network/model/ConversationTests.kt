package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ConversationTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json: String = javaClass.classLoader.getResource("response_conversation_required.json").readText()

        // when
        val conversation = Json.decodeFromString<Conversation>(json)

        // then
        Truth.assertThat(conversation).isNotNull()
        Truth.assertThat(conversation.id).isEqualTo("418450")
        Truth.assertThat(conversation.accounts).isNotNull()
        Truth.assertThat(conversation.accounts.size).isEqualTo(1)
        Truth.assertThat(conversation.unread).isEqualTo(true)
        Truth.assertThat(conversation.lastStatus).isNotNull()
        Truth.assertThat(conversation.lastStatus.id).isEqualTo("103270115826048975")
    }
}
