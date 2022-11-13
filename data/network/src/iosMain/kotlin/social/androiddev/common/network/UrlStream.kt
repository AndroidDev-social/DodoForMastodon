package social.androiddev.common.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.utils.io.core.use

actual suspend fun urlStream(url: String): ByteArray = HttpClient().use {
    it.get(urlString = url).body()
}
