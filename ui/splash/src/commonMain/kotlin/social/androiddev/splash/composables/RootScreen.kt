package social.androiddev.splash.composables

import WelcomeScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

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

                is RootComponent.Child.WelcomeScreenChild -> {
                    WelcomeScreen(
                        modifier = Modifier.fillMaxSize(),
                        component = child.component
                    )
                }
                RootComponent.Child.TimelineScreenChild -> {
//                    TODO: TimelineScreen()
                }
                RootComponent.Child.SelectServerScreenChild -> {
//                    TODO: SelectServerScreen()
                }
            }
        }
    }
}