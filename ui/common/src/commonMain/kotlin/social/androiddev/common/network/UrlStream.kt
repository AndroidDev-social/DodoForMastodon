package social.androiddev.common.network

import java.io.ByteArrayInputStream

expect suspend fun urlStreamOf(url: String): ByteArrayInputStream
