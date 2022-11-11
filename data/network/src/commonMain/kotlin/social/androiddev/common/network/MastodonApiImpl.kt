package social.androiddev.common.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.serialization.*
import kotlinx.serialization.SerializationException
import social.androiddev.common.network.model.Instance

class MastodonApiImpl(
    private val httpClient: HttpClient,

): MastodonApi {
    override suspend fun getInstance(domain: String?): Result<Instance> {
        return try {
            Result.success(
                httpClient.get("/api/v1/instance") {
                    domain?.let {
                        headers.append("domain", domain)
                    }
                }.body()
            )
        } catch (exception: SerializationException) {
            Result.failure(exception = exception)
        } catch (exception: ResponseException) {
            Result.failure(exception = exception)
        } catch (exception: JsonConvertException) {
            Result.failure(exception = exception)
        }
    }
}
