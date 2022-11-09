package social.androiddev.common.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import social.androiddev.common.network.urlStream
import java.io.ByteArrayInputStream

actual suspend fun loadImageIntoPainter(url: String): Painter {
    val inputStream: ByteArrayInputStream = urlStream(url)
    val imageBitmap: ImageBitmap = loadImageBitmap(inputStream = inputStream)

    return BitmapPainter(imageBitmap)
}
