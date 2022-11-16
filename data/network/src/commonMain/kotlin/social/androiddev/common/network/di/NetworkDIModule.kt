package social.androiddev.common.network.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol

// TODO Update with Koin
object NetworkDIModule {

    fun createHttpClient(domain: String): HttpClient {
        return HttpClient {
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = domain
                }
            }
        }
    }
}