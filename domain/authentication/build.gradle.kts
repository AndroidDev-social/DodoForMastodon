plugins {
    id("social.androiddev.library")
}

android {
    namespace = "social.androiddev.domain.authentication"
}

kotlin {
    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(libs.io.insert.koin.core)

            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.org.jetbrains.kotlin.test.common)
                implementation(libs.org.jetbrains.kotlin.test.annotations.common)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.io.ktor.server.core)
                implementation(libs.io.ktor.server.netty)
            }
        }
    }
}