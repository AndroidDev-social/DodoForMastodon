package social.androiddev.signedout.selectserver

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import social.androiddev.common.web.WebOpenExtras

@Composable
actual fun webOpenExtras(): WebOpenExtras {
    return WebOpenExtras(
        primaryColor = MaterialTheme.colors.primary.toArgb(),
        secondaryColor = MaterialTheme.colors.secondary.toArgb(),
    )
}