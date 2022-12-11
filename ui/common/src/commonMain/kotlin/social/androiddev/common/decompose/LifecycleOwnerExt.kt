package social.androiddev.common.decompose

import com.arkivanov.essenty.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

fun LifecycleOwner.coroutineScope(context: CoroutineContext): CoroutineScope =
    CoroutineScope(context, lifecycle)