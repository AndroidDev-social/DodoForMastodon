/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.signin

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SignInContent(
    component: SignInComponent,
    modifier: Modifier = Modifier,
) {

    val state by component.state.collectAsState()

    Column(
        modifier = modifier
    ) {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text("Login")
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier.clickable {
                        component.onCloseClicked()
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                )
            }
        )

        AnimatedVisibility(state.error != null) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .background(color = MaterialTheme.colors.error.copy(alpha = 0.5F))
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color =
                            MaterialTheme.colors.error
                        ),
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Something is wrong!",
                    color = MaterialTheme.colors.onError,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
        if (state.oauthAuthorizeUrl.isNotEmpty()) {
            SignInWebView(
                url = state.oauthAuthorizeUrl,
                modifier = Modifier.fillMaxSize(),
                shouldCancelLoadingUrl = component::shouldCancelLoadingUrl,
                onWebError = component::onErrorFromOAuth
            )
        }
    }
}
