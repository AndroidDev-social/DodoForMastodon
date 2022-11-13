//import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import social.androiddev.common.composables.buttons.MastodonButton
import social.androiddev.common.composables.buttons.MastodonOutlinedButton
import social.androiddev.common.theme.Blue
import social.androiddev.common.theme.MastodonTheme
import social.androiddev.common.utils.AsyncImage
import social.androiddev.common.utils.loadImageIntoPainter

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
    appIcon: @Composable () -> Unit = {
        AsyncImage(
            load = { loadImageIntoPainter(url = "https://via.placeholder.com/200x200/6FA4DE/010041?text=MastodonX") },
            painterFor = { remember { it } },
            contentDescription = "App Logo",
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .size(240.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
        )
    },
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colors.surface,
                                Blue,
                                Blue,
                                MaterialTheme.colors.surface,
                            )
                        ),
                    )
                    .padding(top = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                appIcon()

                Spacer(Modifier.height(32.dp))
            }

            Text(
                text = "MastodonX",
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Welcome to a completely free and decentralized social media.",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(Modifier.height(42.dp))

            MastodonButton(
                modifier = Modifier
                    .widthIn(min = 240.dp)
                    .padding(horizontal = 24.dp),
                onClick = navigateToLogin,
                text = "Log In"
            )

            Spacer(Modifier.height(8.dp))

            MastodonOutlinedButton(
                modifier = Modifier
                    .widthIn(min = 240.dp)
                    .padding(horizontal = 24.dp),
                onClick = navigateToSignUp,
                text = "Sign Up"
            )
        }
    }
}

//@Preview
@Composable
private fun PreviewWelcomeScreen() {
    MastodonTheme(true) {
        WelcomeScreen(
            navigateToLogin = {},
            navigateToSignUp = {},
        )
    }
}