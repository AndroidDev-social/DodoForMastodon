package social.androiddev.common.network

import java.io.ByteArrayInputStream

expect suspend fun urlStream(url: String): ByteArrayInputStream
