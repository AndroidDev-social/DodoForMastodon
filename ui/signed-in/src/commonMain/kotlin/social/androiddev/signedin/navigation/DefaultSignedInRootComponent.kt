/*
 * This file is part of MastodonX.
 *
 * MastodonX is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * MastodonX is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with MastodonX. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedin.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import social.androiddev.timeline.navigation.DefaultTimelineComponent

class DefaultSignedInRootComponent(
    private val componentContext: ComponentContext
) : SignedInRootComponent, ComponentContext by componentContext {

    // StackNavigation accepts navigation commands and forwards them to all subscribed observers.
    private val navigation = StackNavigation<Config>()

    // ChildStack is a simple data class that stores a stack of components and their configurations.
    private val stack: Value<ChildStack<Config, SignedInRootComponent.Child>> = childStack(
        source = navigation,
        initialStack = { listOf(Config.Timeline) },
        handleBackButton = true, // Pop the back stack on back button press
        childFactory = ::createChild
    )

    override val childStack: Value<ChildStack<*, SignedInRootComponent.Child>> = stack

    private fun createChild(config: Config, componentContext: ComponentContext): SignedInRootComponent.Child =
        when (config) {
            Config.Timeline -> {
                SignedInRootComponent.Child.Timeline(createTimelineComponent(componentContext))
            }
        }

    private fun createTimelineComponent(
        componentContext: ComponentContext,
    ) = DefaultTimelineComponent(
        componentContext = componentContext
    )

    /**
     * Supported configurations for all children in this root.
     * A "configuration" represents a child component
     * and contains all it's arguments.
     * These configurations are persisted and restored on events like
     * configuration changes, process death etc...
     *
     * All Configurations must:
     * 1) Be immutable
     * 2) Implement `equals()` and `hashCode()`
     * 3) Implement Parcelable
     */
    private sealed interface Config : Parcelable {

        @Parcelize
        object Timeline : Config
    }
}
