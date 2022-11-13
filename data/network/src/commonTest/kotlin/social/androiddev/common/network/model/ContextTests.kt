package social.androiddev.common.network.model

import kotlin.test.assertEquals
import kotlin.test.Ignore
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNotNull

class ContextTests {
    // TODO: fix loading json from resources
    @Ignore
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        // val json: String = javaClass.classLoader.getResource("response_context_required.json").readText()
        val json: String = ""

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
