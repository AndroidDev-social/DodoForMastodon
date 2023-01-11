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
package social.androiddev.signedin.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Represents a different state of navigation header (title, actions) based on active route
 */
internal sealed class NavHeaderSettings(
    val title: String = "",
    val headerAction: HeaderActionWithIcon? = null,
) {
    object Empty : NavHeaderSettings("")
    object Settings : NavHeaderSettings("Settings")

    data class Timeline(val action: () -> Unit) :
        NavHeaderSettings(
            "Timeline",
            headerAction = HeaderActionWithIcon(icon = Icons.Default.Settings, action = action)
        )
}

internal data class HeaderActionWithIcon(val icon: ImageVector, val action: () -> Unit)
