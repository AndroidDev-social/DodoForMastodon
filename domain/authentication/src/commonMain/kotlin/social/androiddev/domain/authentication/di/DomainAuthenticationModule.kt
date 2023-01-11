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
package social.androiddev.domain.authentication.di

import org.koin.core.module.Module
import org.koin.dsl.module
import social.androiddev.domain.authentication.usecase.AuthenticateClient
import social.androiddev.domain.authentication.usecase.CreateAccessToken
import social.androiddev.domain.authentication.usecase.GetAuthStatus
import social.androiddev.domain.authentication.usecase.GetSelectedApplicationOAuthToken
import social.androiddev.domain.authentication.usecase.LogoutFromCurrentServer

/**
 * Koin module containing all koin/bean definitions for
 * domain related classes for authentication
 */
val domainAuthModule: Module = module {

    factory {
        AuthenticateClient(
            authenticationRepository = get(),
        )
    }

    factory {
        GetSelectedApplicationOAuthToken(
            authenticationRepository = get(),
        )
    }

    factory {
        CreateAccessToken(
            authenticationRepository = get(),
        )
    }

    factory {
        GetAuthStatus(
            authenticationRepository = get()
        )
    }

    factory {
        LogoutFromCurrentServer(
            authenticationRepository = get()
        )
    }
}
