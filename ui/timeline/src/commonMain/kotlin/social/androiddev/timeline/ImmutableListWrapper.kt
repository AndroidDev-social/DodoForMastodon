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
