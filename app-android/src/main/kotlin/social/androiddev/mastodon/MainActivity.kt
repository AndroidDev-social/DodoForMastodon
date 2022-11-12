package social.androiddev.mastodon

import WelcomeScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import social.androiddev.common.theme.MastodonTheme

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MastodonTheme {
                WelcomeScreen(
                    modifier = Modifier.fillMaxSize(),
                    navigateToSignUp = {},
                    navigateToLogin = {},
                    appIcon = {
                        Image(
                            painter = painterResource(R.drawable.ic_elephant),
                            contentDescription = "app logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = 64.dp)
                                .fillMaxWidth()
                                .aspectRatio(1f)
                        )
                    }
                )
            }
        }
    }
}
