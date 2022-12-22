package social.androiddev.desktop.web

import androidx.compose.ui.res.painterResource
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import social.androiddev.common.web.WebAuth
import social.androiddev.common.web.WebOpenExtras

private val Browsers = arrayOf(
    "xdg-open",
    "google-chrome",
    "firefox",
    "opera",
    "konqueror",
    "mozilla"
)

class ExternalBrowserWebAuth : WebAuth {

    private val _state = MutableSharedFlow<WebAuth.State>(replay = 1)
    override val state: Flow<WebAuth.State> = _state

    override suspend fun start(): String {
        val server = io.ktor.server.engine.embeddedServer(io.ktor.server.netty.Netty, port = 0) {
            routing {
                get("/callback") {
                    val code = call.request.queryParameters["code"]
                    if (code != null) {
                        _state.emit(WebAuth.State.Success(code))
                        this@embeddedServer.dispose()
                        call.respond("Success! You may close the tab")
                    }
                    val error = call.request.queryParameters["error"]
                    val errorDescription = call.request.queryParameters["error_description"]
                    if (error != null) {
                        _state.emit(WebAuth.State.Error(errorDescription))
                        call.respond("$error: $errorDescription")
                    }
                }
            }
        }
        server.start()
        val port = server.resolvedConnectors().first().port
        return "http://localhost:${port}/callback"
    }

    override fun open(uri: String, extras: WebOpenExtras) {
        val osName = System.getProperty("os.name")
        try {
            if (osName.startsWith("Mac OS")) {
                Runtime.getRuntime().exec(
                    "open $uri"
                )
            } else if (osName.startsWith("Windows")) {
                Runtime.getRuntime().exec(
                    "rundll32 url.dll,FileProtocolHandler $uri"
                )
            } else { //assume Unix or Linux
                var browser: String? = null
                for (b in Browsers) {
                    if (browser == null && Runtime.getRuntime()
                            .exec(arrayOf("which", b)).inputStream.read() != -1
                    ) {
                        Runtime.getRuntime().exec(arrayOf(b.also { browser = it }, uri))
                    }
                }
                if (browser == null) {
                    throw Exception("No web browser found")
                }
            }
        } catch (e: Exception) {
            // should not happen
            // dump stack for debug purpose
            e.printStackTrace()
        }
    }
}