/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.common.network.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException

/**
 * Executes [block] and returns the [Result]. If an error occurs then [block] will be
 * retried a maximum of [maxRetry] times. Caution - this should typically be used only on
 * worker/background threads.
 */
inline fun <reified T> runCatchingRetry(maxRetry: Int, block: () -> T): Result<T> {
    var result: Result<T>
    var retryCount = 0
    do {
        result = runCatching(block)
        retryCount++
    } while (result.isFailure && retryCount <= maxRetry)
    return result
}

/**
 * Executes [block] and unwraps the [Result]. If a failure occurs then [default] will be returned.
 */
inline fun <T> runOrDefault(block: () -> T, default: (Throwable) -> T): T =
    runCatching(block).fold(
        onSuccess = { it },
        onFailure = default,
    )

/**
 * Executes [block] and returns a [Result]. All exceptions thrown are
 * returned as a [Result.Failure] except a [kotlinx.coroutines.CancellationException]
 * which will be rethrown.
 *
 * See [Throwable.isCancellation] for more info.
 */
inline fun <T> runCatchingIgnoreCancelled(block: () -> T): Result<T> = runCatching(block)
    .onFailure { error ->
        if (error.isCancellation()) {
            throw error
        }
    }

/**
 * Executes [block] and returns the [Result]. If an error occurs then [block] will be
 * retried a maximum of [maxRetry] times. Caution - this should typically be used only on
 * worker/background threads. This is slightly different from [runCatchingRetry] in that
 * a [CancellationException] will not be caught and will be rethrown.
 *
 * See [Throwable.isCancellation] for more info.
 */
inline fun <reified T> runCatchingRetryIgnoreCanceled(maxRetry: Int, block: () -> T): Result<T> {
    var result: Result<T>
    var retryCount = 0
    do {
        result = runCatchingIgnoreCancelled(block)
        retryCount++
    } while (result.isFailure && retryCount <= maxRetry)
    return result
}

/**
 * Extension on a [Throwable] that returns true if it is of type [CancellationException]. Typically,
 * this type of Exception should _not_ be caught and should be bubbled up. Coroutines internally
 * uses [CancellationException] for cancelling of work, and they are ignored by all handlers by default.
 *
 * @See https://kotlinlang.org/docs/exception-handling.html#cancellation-and-exceptions
 */
fun Throwable.isCancellation() = this is CancellationException && this !is TimeoutCancellationException
