/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.dodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import social.androiddev.common.theme.DodoTheme
import social.androiddev.root.composables.RootContent
import social.androiddev.root.navigation.DefaultRootComponent

class MainActivity : AppCompatActivity() {

    private val activityModule = module {
        factory<ComponentActivity> { this@MainActivity }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(activityModule)

        // Create the root component before starting Compose
        val root = DefaultRootComponent(
            componentContext = defaultComponentContext(),
            mainContext = Dispatchers.Main,
        )

        setContent {
            DodoTheme {
                RootContent(
                    component = root,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(activityModule)
    }
}
