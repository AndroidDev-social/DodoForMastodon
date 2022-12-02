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
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
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
