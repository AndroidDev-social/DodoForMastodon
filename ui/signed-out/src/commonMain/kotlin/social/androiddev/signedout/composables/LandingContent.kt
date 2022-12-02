/*
 * This file is part of Dodo.
 *
 * Dodo is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * Dodo is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Dodo. If not, see <https://www.gnu.org/licenses/>.
 */
package social.androiddev.signedout.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import social.androiddev.common.theme.Blue
import social.androiddev.common.theme.DodoTheme
import social.androiddev.common.utils.AsyncImage
import social.androiddev.common.utils.loadImageIntoPainter
import social.androiddev.signedout.navigation.LandingComponent

/**
 * Landing view that delegates business logic to [LandingContent]
 */
@Composable
fun LandingContent(
    component: LandingComponent,
    modifier: Modifier = Modifier,
    appIcon: @Composable () -> Unit = {
        AsyncImage(
            load = { loadImageIntoPainter(url = "https://via.placeholder.com/200x200/6FA4DE/010041?text=Dodo") },
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
    LandingContent(
        modifier = modifier,
        onGetStartedClicked = component::onGetStartedClicked,
        appIcon = appIcon,
    )
}

@Composable
fun LandingContent(
    modifier: Modifier = Modifier,
    onGetStartedClicked: () -> Unit,
    appIcon: @Composable () -> Unit = {
        AsyncImage(
            load = { loadImageIntoPainter(url = "https://via.placeholder.com/200x200/6FA4DE/010041?text=Dodo") },
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
                text = "Dodo",
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp),
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Welcome to a completely free and decentralized social media.",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 24.dp),
            )

            Spacer(Modifier.height(42.dp))

            MastodonButton(
                modifier = Modifier
                    .widthIn(min = 240.dp)
                    .padding(horizontal = 24.dp),
                onClick = onGetStartedClicked,
                text = "Get Started",
            )
        }
    }
}

// @Preview
@Composable
private fun PreviewLandingContent() {
    DodoTheme(true) {
        LandingContent(
            onGetStartedClicked = {},
        )
    }
}
