package social.androiddev.desktop

import WelcomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import social.androiddev.common.theme.MastodonTheme

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
