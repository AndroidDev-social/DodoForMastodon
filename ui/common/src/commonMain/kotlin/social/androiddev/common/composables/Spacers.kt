/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object Spacers {
    @Composable
    fun XSmall(modifier: Modifier = Modifier) = Spacer(modifier = modifier.size(2.dp))

    @Composable
    fun Small(modifier: Modifier = Modifier) = Spacer(modifier = modifier.size(4.dp))

    @Composable
    fun Default(modifier: Modifier = Modifier) = Spacer(modifier = modifier.size(8.dp))

    @Composable
    fun Medium(modifier: Modifier = Modifier) = Spacer(modifier = modifier.size(12.dp))

    @Composable
    fun Large(modifier: Modifier = Modifier) = Spacer(modifier = modifier.size(16.dp))

    @Composable
    fun XLarge(modifier: Modifier = Modifier) = Spacer(modifier = modifier.size(24.dp))

    @Composable
    fun XXLarge(modifier: Modifier = Modifier) = Spacer(modifier = modifier.size(32.dp))
}
