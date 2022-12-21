plugins {
    id("social.androiddev.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "social.androiddev.common.network"
}

kotlin {
    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.core)
                implementation(libs.io.ktor.client.serialization)
                implementation(libs.io.ktor.serialization.kotlinx.json)
                implementation(libs.io.ktor.client.content.negotiation)
                implementation(libs.io.ktor.client.auth)
                implementation(libs.io.ktor.client.logging)
                implementation(libs.org.jetbrains.kotlinx.serialization.json)
                implementation(libs.io.insert.koin.core)
                implementation(libs.io.github.aakira.napier)
            }
        }

        // android
        val androidMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.cio)
            }
        }

        // desktop
        val desktopMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.cio)
            }
        }

        // iOS
        val iosMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }

        // testing
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.io.ktor.client.mock)
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
                implementation(libs.io.insert.koin.test)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.io.ktor.client.mock.jvm)
            }
        }
        val desktopTest by getting {
            dependencies {
                implementation(libs.io.ktor.client.mock.jvm)
            }
        }
    }
}
