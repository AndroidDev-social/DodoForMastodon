package social.androiddev.common.network.util

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException

inline fun <reified T> runCatchingRetry(maxRetry: Int, block: () -> T): Result<T> {
    var result: Result<T>
    var retryCount = 0
    do {
        result = runCatching(block)
        retryCount++
    } while (result.isFailure && retryCount <= maxRetry)
    return result
}

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

inline fun <reified T> runCatchingRetryIgnoreCanceled(maxRetry: Int, block: () -> T): Result<T> {
    var result: Result<T>
    var retryCount = 0
    do {
        result = runCatchingIgnoreCancelled(block)
        retryCount++
    } while (result.isFailure && retryCount <= maxRetry)
    return result
}

fun Throwable.isCancellation() = this is CancellationException && this !is TimeoutCancellationException