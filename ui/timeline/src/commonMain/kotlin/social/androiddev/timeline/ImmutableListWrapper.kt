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
package social.androiddev.timeline

import androidx.compose.runtime.Immutable

/**
 *  The Compose Compiler cannot infer the stability of a parameter if a List is used in it,
 *  even if the item type is stable. This wrapper class uses the [Immutable] annotation
 *  to promise the compiler it is indeed Immutable.
 */
@Immutable
data class ImmutableListWrapper<T : Any?>(
    val items: List<T>
) {
    companion object {
        inline fun <reified T> empty() = ImmutableListWrapper(emptyList<T>())
    }
}
