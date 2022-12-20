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
package social.androiddev.common.persistence.di

import org.koin.core.module.Module

/**
 * Expect a platform specific Koin module containing all
 * bean definitions for persistent storage related classes
 */
expect val persistenceModule: Module

internal const val AUTH_DB_NAME = "authentication.db"
internal const val AUTH_SETTINGS_NAME = "DodoAuthSettings"
