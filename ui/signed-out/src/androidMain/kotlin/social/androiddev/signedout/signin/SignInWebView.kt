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
package social.androiddev.signedout.signin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.OnNewIntentProvider
import androidx.core.util.Consumer

@Composable
actual fun SignInWebView(
    url: String,
    onWebError: (message: String) -> Unit,
    onCancel: () -> Unit,
    shouldCancelLoadingUrl: (url: String) -> Boolean,
    modifier: Modifier,
) {

    val webIntent = webBrowserIntent(url, MaterialTheme.colors.primary, MaterialTheme.colors.secondary)
    val handler = Handler(Looper.getMainLooper())

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_CANCELED) {
                // post to a handler to wait for a redirect intent as that should supersede this
                handler.post { onCancel() }
            }
        }

    OnNewIntent { intent ->
        val redirectUrl = intent?.data?.toString()
        if (redirectUrl != null) {
            if (shouldCancelLoadingUrl(redirectUrl)) {
                handler.removeCallbacksAndMessages(null)
            } else {
                onCancel()
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            Modifier
                .align(Alignment.Center)
                .size(84.dp)
        )
    }
    DisposableEffect(url) {
        launcher.launch(webIntent)
        onDispose {
            handler.removeCallbacksAndMessages(null)
        }
    }
}

private fun webBrowserIntent(url: String, primaryColor: Color, secondaryColor: Color): Intent {
    val intent = CustomTabsIntent.Builder()
        .setToolbarColor(primaryColor.toArgb())
        .setSecondaryToolbarColor(secondaryColor.toArgb())
        .build()
        .intent
    intent.data = Uri.parse(url)
    return intent
}

@Composable
private fun OnNewIntent(callback: (Intent?) -> Unit) {
    val context = LocalContext.current
    val newIntentProvider = context as OnNewIntentProvider

    val listener = remember(newIntentProvider) { Consumer<Intent?> { callback(it) } }

    DisposableEffect(listener) {
        newIntentProvider.addOnNewIntentListener(listener)
        onDispose {
            newIntentProvider.removeOnNewIntentListener(listener)
        }
    }
}
