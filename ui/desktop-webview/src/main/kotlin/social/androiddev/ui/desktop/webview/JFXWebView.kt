/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.ui.desktop.webview

import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.web.WebView

internal class JFXWebView(
    private val url: String,
    private val onUrlOfCurrentPageChanged: (newUrl: String) -> Unit,
) : JFXPanel() {
    init {
        // FIXME : I added this line to avoid the following behavior:
        // If the user goes back and then restarts the webview the initialization is not done
        // may be because of Decompose navigation.pop() ??
        Platform.setImplicitExit(false)

        Platform.runLater(::initialiseJavaFXScene)
    }

    private fun initialiseJavaFXScene() {
        val webView = WebView()
        webView.pageFill = Color.TRANSPARENT
        webView.engine.locationProperty().addListener { observable, _, _ ->
            onUrlOfCurrentPageChanged(observable.value)
        }

        val scene = Scene(webView, Color.TRANSPARENT)
        setScene(scene)

        webView.engine.load(url)
    }
}
