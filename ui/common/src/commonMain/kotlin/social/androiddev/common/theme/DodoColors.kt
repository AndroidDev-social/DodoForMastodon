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
