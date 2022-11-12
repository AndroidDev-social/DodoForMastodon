package social.androiddev.desktop

import WelcomeScreen
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.common.utils.AsyncImage
import social.androiddev.common.utils.loadImageIntoPainter
import social.androiddev.timeline.TimelineScreen
import social.androiddev.timeline.dummyFeedItem

fun main() {
    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "MastodonX"
        ) {
            MastodonTheme {
                WelcomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToSignUp = {},
                    navigateToLogin = {},
                )
            }
        }
    }
}
