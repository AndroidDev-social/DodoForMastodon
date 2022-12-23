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
package social.androiddev.common.composables.buttons

// import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ButtonColors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import social.androiddev.common.theme.DodoTheme

@Composable
fun DodoTextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = textButtonColors(),
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier,
        enabled = enabled,
        colors = colors,
        onClick = onClick
    ) {
        Text(text.uppercase())
    }
}

/**
 * Creates a ButtonColors that represents the default background and content colors used in a TextButton.
 * Params:
 * [backgroundColor] - the background color of this TextButton
 * [contentColor] - the content color of this TextButton when enabled
 * [disabledContentColor] - the content color of this TextButton when not enabled
 */
@Composable
fun textButtonColors(
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = MaterialTheme.colors.primary,
    disabledContentColor: Color = MaterialTheme.colors.onSurface
        .copy(alpha = ContentAlpha.disabled)
): ButtonColors = DefaultTextButtonColors(
    backgroundColor = backgroundColor,
    contentColor = contentColor,
    disabledBackgroundColor = backgroundColor,
    disabledContentColor = disabledContentColor
)

/**
 * Default [ButtonColors] implementation for Themed Text Buttons.
 */
@Immutable
private class DefaultTextButtonColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color
) : ButtonColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultTextButtonColors

        if (backgroundColor != other.backgroundColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledBackgroundColor != other.disabledBackgroundColor) return false
        if (disabledContentColor != other.disabledContentColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledBackgroundColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}

// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
// @Preview
@Composable
private fun PreviewTextButtonLight() {
    DodoTheme(
        useDarkTheme = false,
    ) {
        DodoTextButton(
            modifier = Modifier.wrapContentSize(),
            text = "Action",
            onClick = {}
        )
    }
}

// skip preview to work with multiplatform
// https://github.com/JetBrains/compose-jb/issues/1603
// @Preview
@Composable
private fun PreviewTextButtonDark() {
    DodoTheme(
        useDarkTheme = true,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DodoTextButton(
                modifier = Modifier.fillMaxWidth(),
                text = "This is a long button",
                onClick = {}
            )

            DodoTextButton(
                modifier = Modifier.wrapContentSize(),
                text = "Action",
                onClick = {}
            )

            DodoTextButton(
                modifier = Modifier.wrapContentSize(),
                text = "Disabled",
                onClick = {}
            )
        }
    }
}
