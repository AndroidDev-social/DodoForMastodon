package social.androiddev.common.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import social.androiddev.common.network.urlStream
import java.io.ByteArrayInputStream

actual suspend fun loadImageIntoPainter(url: String): Painter {
    val inputStream: ByteArrayInputStream = urlStream(url)
    val byteArray: ByteArray = inputStream.readBytes()
    val bitmap: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

    return BitmapPainter(bitmap.asImageBitmap())
}
