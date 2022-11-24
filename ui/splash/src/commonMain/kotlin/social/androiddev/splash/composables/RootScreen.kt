package social.androiddev.splash.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import social.androiddev.splash.navigation.RootComponent

@Composable
fun RootScreen(
    component: RootComponent,
    modifier: Modifier = Modifier
) {
    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    Children(
        stack = childStack,
        modifier = Modifier.fillMaxSize()
    ) { createdChild ->
        when (val child = createdChild.instance) {
            is RootComponent.Child.SplashScreenChild -> {
                SplashScreen(
                    modifier = Modifier.fillMaxSize(),
                    component = child.component
                )
            }
            RootComponent.Child.LoggedOutChild -> {
//                WelcomeScreen()
            }
            RootComponent.Child.TimelineChild -> {
//                TimelineScreen()
            }
        }
    }
}