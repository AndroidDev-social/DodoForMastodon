package social.androiddev.mastodon

import WelcomeScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import social.androiddev.common.theme.MastodonTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MastodonTheme {
                WelcomeScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}