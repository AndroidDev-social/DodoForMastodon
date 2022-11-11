package social.androiddev.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.timeline.TimelineScreen
import social.androiddev.timeline.dummyFeedItem

fun main() {
    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Mastodon"
        ) {
            MastodonTheme {
                TimelineScreen(listOf(dummyFeedItem))
            }
        }
    }
}
