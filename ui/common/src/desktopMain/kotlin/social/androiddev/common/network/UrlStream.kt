package social.androiddev.common.network

import java.io.ByteArrayInputStream

actual suspend fun urlStreamOf(url: String) : ByteArrayInputStream = url.toUrlStream()
