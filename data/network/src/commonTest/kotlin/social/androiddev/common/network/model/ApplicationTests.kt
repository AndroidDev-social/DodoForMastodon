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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ApplicationTests {
    @Test
    fun `deserialize required fields should succeed`() {
        // given
        val json = """
        {
            "id": "123",
            "name": "test app",
            "client_id": "bgorLrj8s1CeX_QghuI5NhVsestPXkTyyCBuaSCeYj4",
            "client_secret": "lNmvmMA8_deuGdQOsuZ_dqE7zxQOoociwfTlHB-L1C0",
            "website": null,
            "vapid_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
        """.trimIndent()

        // when
        val application = Json.decodeFromString<Application>(json)

        // then
        assertEquals(expected = "test app", actual = application.name)
        assertNull(actual = application.website)
        assertEquals(expected = "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M=", actual = application.vapidKey)
        assertEquals(expected = "bgorLrj8s1CeX_QghuI5NhVsestPXkTyyCBuaSCeYj4", actual = application.clientId)
        assertEquals(expected = "lNmvmMA8_deuGdQOsuZ_dqE7zxQOoociwfTlHB-L1C0", actual = application.clientSecret)
    }
}
