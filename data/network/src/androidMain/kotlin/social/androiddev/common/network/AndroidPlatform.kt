package social.androiddev.common.network

class AndroidPlatform: Platform {
    override val name: String = "Android"
    override val version: String = "0.1.0"
    override val build: String = "1"
}

actual fun getPlatform(): Platform = AndroidPlatform()
