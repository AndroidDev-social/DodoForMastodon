package social.androiddev.common.network.model

import com.google.common.truth.Truth
import java.io.InputStream
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ContextTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json: String = javaClass.classLoader.getResource("response_context_required.json").readText()

        // when
        val context = Json.decodeFromString<Context>(json)

        // then
        Truth.assertThat(context).isNotNull()
        Truth.assertThat(context.ancestors).isNotNull()
        Truth.assertThat(context.ancestors.size).isEqualTo(1)
        Truth.assertThat(context.descendants).isNotNull()
        Truth.assertThat(context.descendants.size).isEqualTo(1)

        val firstAncestor = context.ancestors[0]
        Truth.assertThat(firstAncestor.id).isEqualTo("103270115826048975")

        val firstDescendants = context.descendants[0]
        Truth.assertThat(firstDescendants.id).isEqualTo("103270115826048975")
    }
}
