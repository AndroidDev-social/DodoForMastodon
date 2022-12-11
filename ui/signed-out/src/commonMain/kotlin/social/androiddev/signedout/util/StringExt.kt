package social.androiddev.signedout.util

import java.net.URLEncoder

fun String.encode(): String = URLEncoder.encode(this, "UTF-8")