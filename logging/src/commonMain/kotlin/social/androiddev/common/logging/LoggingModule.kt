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
package social.androiddev.common.logging

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.dsl.module

val loggingModule = module {
    // todo: provide different antilog for release
    Napier.base(DebugAntilog())
}
