package social.androiddev.common.network.model

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ApplicationTests {
    @Test
    fun `deserialize required fields should succeed`() = runBlocking {
        // given
        val json = """
        {
            "name": "test app",
            "website": null,
            "vapid_key": "BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M="
        }
        """.trimIndent()

        // when
        val application = Json.decodeFromString<Application>(json)

        // then
        Truth.assertThat(application.name).isEqualTo("test app")
        Truth.assertThat(application.website).isNull()
        Truth.assertThat(application.vapidKey).isEqualTo("BCk-QqERU0q-CfYZjcuB6lnyyOYfJ2AifKqfeGIm7Z-HiTU5T9eTG5GxVA0_OH5mMlI4UkkDTpaZwozy0TzdZ2M=")
        Truth.assertThat(application.clientId).isNull()
        Truth.assertThat(application.clientSecret).isNull()
    }
}
