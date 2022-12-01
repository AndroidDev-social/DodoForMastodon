/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.composables.webview

import android.annotation.SuppressLint
import android.graphics.Color
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun SignInWebView(
    server: String,
    onSingedIn: () -> Unit,
    onFailed: (error: String) -> Unit
) {

    val signInViewModel = remember(server) { SignInViewModel(server) }

    AndroidView(
        factory = {
            WebView(it).apply {
                setBackgroundColor(Color.TRANSPARENT)
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                    }

                    override fun onReceivedError(
                        view: WebView,
                        request: WebResourceRequest,
                        error: WebResourceError
                    ) {
                        onFailed(error.toString())
                    }

                    override fun shouldOverrideUrlLoading(
                        view: WebView,
                        request: WebResourceRequest
                    ): Boolean {
                        return shouldOverrideUrlLoading(request.url.toString())
                    }

                    /* overriding this deprecated method is necessary for it to work on api levels < 24 */
                    @Suppress("OVERRIDE_DEPRECATION")
                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        urlString: String?
                    ): Boolean {
                        if (urlString == null) return false
                        return shouldOverrideUrlLoading(urlString)
                    }

                    fun shouldOverrideUrlLoading(url: String): Boolean {
                        signInViewModel.resolveSignInStatusFromUrl(

                            url = url,
                            onSingedIn = onSingedIn,
                            onFailed = onFailed
                        )
                        return false
                    }
                }

                // JavaScript needs to be enabled because otherwise 2FA does not work in some instances
                @SuppressLint("SetJavaScriptEnabled")
                settings.javaScriptEnabled = true

                loadUrl(signInViewModel.getSignInUrl())
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
