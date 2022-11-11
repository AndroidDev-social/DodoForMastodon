package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.goncalossilva.resources.Resource
import kotlin.test.Test
import kotlin.test.assertNotNull

class ContextTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json: String = Resource("src/commonTest/resources/response_context_required.json").readText()

        // when
        val context = Json.decodeFromString<Context>(json)

        // then
        assertNotNull(actual = context)
        assertNotNull(actual = context.ancestors)
        assertEquals(expected = 1, actual = context.ancestors.size)
        assertNotNull(actual = context.descendants)
        assertEquals(expected = 1, actual = context.descendants.size)

        val firstAncestor = context.ancestors[0]
        assertEquals(expected = "103270115826048975", actual = firstAncestor.id)

        val firstDescendants = context.descendants[0]
        assertEquals(expected = "103270115826048975", actual = firstDescendants.id)
    }
}
