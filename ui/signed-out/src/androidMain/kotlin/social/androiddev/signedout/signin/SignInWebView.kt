/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.signin

import android.graphics.Color
import android.webkit.CookieManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebStorage
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun SignInWebView(
    modifier: Modifier,
    url: String,
    onWebError: (message: String) -> Unit,
    shouldCancelLoadingUrl: (url: String) -> Boolean,
) {

    DisposableEffect(Unit) {
        onDispose {
            // Remove user session from WebView
            WebStorage.getInstance().deleteAllData()
            CookieManager.getInstance().removeAllCookies(null)
        }
    }

    AndroidView(
        modifier = modifier,
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
                        onWebError(error.toString())
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
                        return if (urlString == null) {
                            false
                        } else {
                            shouldOverrideUrlLoading(urlString)
                        }
                    }

                    fun shouldOverrideUrlLoading(url: String): Boolean {
                        return shouldCancelLoadingUrl(url)
                    }
                }

                // JavaScript needs to be enabled because otherwise 2FA does not work in some instances
                settings.javaScriptEnabled = true
                settings.allowContentAccess = false
                settings.allowFileAccess = false
                settings.databaseEnabled = false
                settings.displayZoomControls = false
                settings.javaScriptCanOpenWindowsAutomatically = false
                settings.userAgentString += " Dodo/1.0"

                loadUrl(url)
            }
        }
    )
}
