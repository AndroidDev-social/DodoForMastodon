package social.androiddev.common.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF4772F5)
val LightBlue = Color(0xff8EC8F0)
val DarkGrey = Color(0xFF1E202A)
val LightGrey = Color(0xFFBABBBE)
val Green = Color(0xff69f0ae)

val darkColors =
    darkColors(
        primary = Blue,
        onPrimary = Color.White,
        secondary = Green,
        onSecondary = LightBlue,
        surface = DarkGrey,
        onSurface = Color.White
    )

val lightColors = lightColors(
    primary = Blue,
    onPrimary = Color.White,
    secondary = Green,
    onSecondary = LightBlue,
    surface = DarkGrey,
    onSurface = Color.White
)
