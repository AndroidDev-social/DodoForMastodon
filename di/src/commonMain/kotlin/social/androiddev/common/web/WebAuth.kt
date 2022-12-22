package social.androiddev.common.web

import kotlinx.coroutines.flow.Flow

interface WebAuth {

    val state: Flow<State>

    suspend fun start(): String

    fun open(uri: String, extras: WebOpenExtras)

    sealed interface State {
        data class Success(val code: String) : State

        data class Error(val error: String? = null) : State
    }
}