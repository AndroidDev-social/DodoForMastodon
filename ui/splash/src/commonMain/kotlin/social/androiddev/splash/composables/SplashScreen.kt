package social.androiddev.splash.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import social.androiddev.splash.navigation.SplashComponent

/**
 * Stateful SplashScreen composable using [SplashComponent] for
 * decompose navigation.
 */
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

/**
 * Stateless composable for rendering a simple Splash Screen
 * upon app launch.
 */
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
            // TODO: Hook up to a DI ViewModel
            if (true) {
                navigateToWelcome()
            } else {
                navigateToTimeline()
            }
        }
    }
}
