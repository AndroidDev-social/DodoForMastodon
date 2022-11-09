package social.androiddev.common.utils

import androidx.compose.ui.graphics.painter.Painter

/**
 * load image from given url as inputStream and put it into a bitmap painter
 */
expect suspend fun loadImageIntoPainter(url: String): Painter
