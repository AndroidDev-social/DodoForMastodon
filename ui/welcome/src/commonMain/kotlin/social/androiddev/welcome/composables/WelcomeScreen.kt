import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 72.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Welcome!")

            Spacer(Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Sign Up")
            }

            Spacer(Modifier.height(8.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Log In")
            }
        }
    }
}
