package social.androiddev.common.network

class DesktopPlatform : Platform {
    override val name: String = "Desktop"
    override val version: String = "0.1.0"
    override val build: String = "1"
}

actual fun getPlatform(): Platform = DesktopPlatform()
