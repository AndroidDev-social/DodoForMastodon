package social.androiddev.common.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import java.io.ByteArrayInputStream

suspend fun String.toUrlStream(): ByteArrayInputStream = HttpClient(CIO).use {
    ByteArrayInputStream(it.get(this).body())
}
