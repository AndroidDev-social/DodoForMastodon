package social.androiddev.common.network

interface Platform {
    val name: String
    val version: String
    val build: String
}

expect fun getPlatform(): Platform
