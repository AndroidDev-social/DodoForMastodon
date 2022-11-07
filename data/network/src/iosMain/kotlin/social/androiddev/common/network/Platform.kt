package social.androiddev.common.network

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName()
    override val version: String = UIDevice.currentDevice.systemVersion
    override val build: String = "1"
}

actual fun getPlatform(): Platform = IOSPlatform()
