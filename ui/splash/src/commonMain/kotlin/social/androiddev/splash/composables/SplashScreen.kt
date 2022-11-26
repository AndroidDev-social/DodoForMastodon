package social.androiddev.splash.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import social.androiddev.splash.navigation.SplashComponent

@Composable
fun SplashScreen(
    modifier: Modifier,
    component: SplashComponent
) {
    SplashScreen(
        modifier = modifier,
        navigateToWelcome = {
            component.navigateToWelcome()
        },
        navigateToTimeline = {
            component.navigateToTimeline()
        }
    )
}


@Composable
fun SplashScreen(
    modifier: Modifier,
    navigateToTimeline: () -> Unit,
    navigateToWelcome: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text("Loading")

        LaunchedEffect(Unit) {
            if (true) {
                navigateToWelcome()
            } else {
                navigateToTimeline()
            }
        }
    }
}
