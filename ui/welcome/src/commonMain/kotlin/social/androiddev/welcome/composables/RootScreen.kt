package social.androiddev.welcome.composables

import WelcomeScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import social.androiddev.welcome.navigation.RootComponent

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
            is RootComponent.Child.WelcomeChild -> {
                WelcomeScreen(
                    navigateToLogin = {
                        child.component.onGetStartedClicked()
                    },
                    navigateToSignUp = {

                    }
                )
            }
            RootComponent.Child.EnterServerChild -> TODO()
        }
    }
}