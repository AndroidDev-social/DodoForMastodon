/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import social.androiddev.common.readBinaryResource

class StatusTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val byteArray: ByteArray = readBinaryResource("src/commonTest/resources/response_status_required.json")
        val json: String = byteArray.decodeToString()

        // when
        val status = Json.decodeFromString<Status>(json)

        // then
        assertNotNull(actual = status)
        assertEquals(expected = "103270115826048975", actual = status.id)
    }
}
