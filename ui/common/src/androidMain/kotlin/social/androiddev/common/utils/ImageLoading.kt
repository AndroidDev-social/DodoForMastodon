package social.androiddev.common.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import social.androiddev.common.network.urlStream

actual suspend fun loadImageIntoPainter(url: String): Painter {
    val byteArray: ByteArray = urlStream(url)
    val bitmap: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

    return BitmapPainter(bitmap.asImageBitmap())
}
