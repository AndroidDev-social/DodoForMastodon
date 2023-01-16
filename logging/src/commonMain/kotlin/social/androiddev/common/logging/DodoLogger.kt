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

import io.github.aakira.napier.Napier

object DodoLogger {

    inline fun d(
        throwable: Throwable? = null,
        tag: String? = null,
        noinline message: () -> String
    ) {
        Napier.d(
            throwable = throwable,
            tag = tag,
            message = message
        )
    }

    inline fun d(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null
    ) {
        Napier.d(
            message = message,
            throwable = throwable,
            tag = tag,
        )
    }

    inline fun i(
        throwable: Throwable? = null,
        tag: String? = null,
        noinline message: () -> String
    ) {
        Napier.i(
            throwable = throwable,
            tag = tag,
            message = message
        )
    }

    inline fun i(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null
    ) {
        Napier.i(
            message = message,
            throwable = throwable,
            tag = tag,
        )
    }

    inline fun v(
        throwable: Throwable? = null,
        tag: String? = null,
        noinline message: () -> String
    ) {
        Napier.v(
            throwable = throwable,
            tag = tag,
            message = message
        )
    }

    inline fun v(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null
    ) {
        Napier.v(
            message = message,
            throwable = throwable,
            tag = tag,
        )
    }

    inline fun w(
        throwable: Throwable? = null,
        tag: String? = null,
        noinline message: () -> String
    ) {
        Napier.w(
            throwable = throwable,
            tag = tag,
            message = message
        )
    }

    inline fun w(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null
    ) {
        Napier.w(
            message = message,
            throwable = throwable,
            tag = tag,
        )
    }

    inline fun e(
        throwable: Throwable? = null,
        tag: String? = null,
        noinline message: () -> String
    ) {
        Napier.e(
            throwable = throwable,
            tag = tag,
            message = message
        )
    }

    inline fun e(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null
    ) {
        Napier.e(
            message = message,
            throwable = throwable,
            tag = tag,
        )
    }

    inline fun assert(
        message: String,
        throwable: Throwable? = null,
        tag: String? = null
    ) {
        Napier.wtf(
            message = message,
            throwable = throwable,
            tag = tag,
        )
    }

    inline fun assert(
        throwable: Throwable? = null,
        tag: String? = null,
        noinline message: () -> String
    ) {
        Napier.wtf(
            throwable = throwable,
            tag = tag,
            message = message
        )
    }
}
