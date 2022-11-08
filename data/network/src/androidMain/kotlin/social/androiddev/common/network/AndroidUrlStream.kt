package social.androiddev.common.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import java.io.ByteArrayInputStream

actual suspend fun urlStream(url: String): ByteArrayInputStream = HttpClient(CIO).use {
    ByteArrayInputStream(it.get(url).body())
}
