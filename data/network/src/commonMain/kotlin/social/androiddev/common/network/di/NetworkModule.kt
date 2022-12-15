/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.di

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import social.androiddev.common.network.MastodonApi
import social.androiddev.common.network.MastodonApiKtor

/**
 * Koin module containing all koin/bean definitions for
 * network/api related classes
 */
val networkModule: Module = module {
    singleOf<HttpClient> {
        HttpClient {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.v(message, null, "HttpClient")
                    }
                }
                level = LogLevel.BODY
                // todo: provide different antilog for release
                Napier.base(DebugAntilog())
            }
            // install plugin so we can use type-safe data models for serialization in ktor
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
//            install(Auth) {
//                bearer {
//                    loadTokens {
//                        // Load tokens from a local storage and return them as the 'BearerTokens' instance
//                        BearerTokens("abc123", "xyz111")
//                    }
//                }
//            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                }
            }
        }
    }

    single<MastodonApi> {
        MastodonApiKtor(
            httpClient = get(),
        )
    }
}
