package social.androiddev.dodo.web

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.util.Consumer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import social.androiddev.common.web.WebAuth
import social.androiddev.common.web.WebOpenExtras

class CustomTabWebAuth(private val activity: ComponentActivity) : WebAuth {

    private val _state = MutableSharedFlow<WebAuth.State>(replay = 1)
    override val state: Flow<WebAuth.State> = _state

    override suspend fun start(): String {
        val onNewIntentListener = Consumer<Intent> { intent ->
            activity.lifecycleScope.launch {
                val code = intent.data?.getQueryParameter("code")
                if (code != null) {
                    _state.emit(WebAuth.State.Success(code))
                }
                val error = intent.data?.getQueryParameter("error")
                val errorDescription = intent.data?.getQueryParameter("error_description")
                if (error != null) {
                    _state.emit(WebAuth.State.Error(errorDescription))
                }
            }
        }
        activity.addOnNewIntentListener(onNewIntentListener)
        return "dodooauth2redirect://callback"
    }

    override fun open(uri: String, extras: WebOpenExtras) {
        CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder()
                    .setToolbarColor(extras.primaryColor)
                    .setSecondaryToolbarColor(extras.secondaryColor)
                    .build()
            )
            .build()
            .launchUrl(activity, Uri.parse(uri))
    }
}