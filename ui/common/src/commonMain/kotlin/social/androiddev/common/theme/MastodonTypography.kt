/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Material Design type scale
 *
 * The Material Design type scale includes a range of contrasting styles that
 * support the needs of your product and its content.
 *
 * The type scale is a combination of thirteen styles that are supported by the type system.
 * It contains reusable categories of text, each with an intended application and meaning.
 */
val MastodonTypography = Typography(

    /** the default FontFamily to be used for TextStyles below */
    defaultFontFamily = FontFamily.Default,

    /** h1 is the largest headline, reserved for short, important text or numerals. */
    h1 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),

    /** h2 is the second-largest headline, reserved for short, important text or numerals. */
    h2 = TextStyle(
        fontWeight = FontWeight.Light,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),

    /** h3 is the third-largest headline, reserved for short, important text or numerals. */
    h3 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),

    /** h4 is the fourth-largest headline, reserved for short, important text or numerals. */
    h4 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 34.sp,
        letterSpacing = 0.25.sp
    ),

    /** h5 is the fifth-largest headline, reserved for short, important text or numerals. */
    h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),

    /** h6 is the sixth-largest headline, reserved for short, important text or numerals. */
    h6 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        letterSpacing = 0.15.sp
    ),

    /**
     * subtitle1 is the largest subtitle, and is typically reserved for medium-emphasis text
     * that is shorter.
     */
    subtitle1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),

    /**
     * subtitle2 is the smallest subtitle, and is typically reserved for medium-emphasis
     * text that is shorter.
     */
    subtitle2 = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),

    /**
     * body1 is the largest body, and is typically used for long-form writing
     * as it works well for small text sizes. For longer sections of text,
     * a serif or sans serif typeface is recommended.
     */
    body1 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),

    /**
     * body2 is the smallest body, and is typically used for long-form writing
     * as it works well for small text sizes. For longer sections of text,
     * a serif or sans serif typeface is recommended.
     */
    body2 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),

    /**
     * button text is a call to action used in different types of
     * buttons (such as text, outlined and contained buttons) and in tabs,
     * dialogs, and cards. Button text is typically sans serif, using all caps text.
     */
    button = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 1.25.sp,
    ),

    /**
     * caption is one of the smallest font sizes. It is used sparingly
     * to annotate imagery or to introduce a headline.
     */
    caption = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),

    /**
     * overline is one of the smallest font sizes. It is used sparingly
     * to annotate imagery or to introduce a headline.
     */
    overline = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.4.sp
    )
)
