package social.androiddev.welcome.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        data class WelcomeChild(val component: WelcomeComponent) : Child()
        object EnterServerChild : Child()
    }
}