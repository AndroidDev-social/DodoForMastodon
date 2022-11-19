plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.diffplug.spotless") version "6.11.0"
}

spotless {
    kotlin {
        target("src/*/kotlin/**/*.kt")
        ktlint("0.43.2")
        licenseHeaderFile(rootProject.file("copyright.kt"))
    }
}

val targetSDKVersion: Int by rootProject.extra
val minSDKVersion: Int by rootProject.extra
val compileSDKVersion: Int by rootProject.extra

android {
    namespace = "social.androiddev.common.network"
    compileSdk = compileSDKVersion

    defaultConfig {
        minSdk = minSDKVersion
        targetSdk = targetSDKVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}

kotlin {
    jvm("desktop")
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.core)
                implementation(libs.io.ktor.client.serialization)
                implementation(libs.io.ktor.serialization.kotlinx.json)
                implementation(libs.io.ktor.client.content.negotiation)
                implementation(libs.org.jetbrains.kotlinx.serialization.json)
            }
        }


        // android
        getByName("androidMain") {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.io.ktor.client.cio)
            }
        }


        // desktop
        getByName("desktopMain") {
            dependencies {
                implementation(libs.io.ktor.client.cio)
            }
        }


        // iOS
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(getByName("commonMain"))

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }


        // testing
        named("androidTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.io.ktor.client.mock.jvm)
                implementation(libs.org.jetbrains.kotlin.test.junit)
            }
        }
        named("desktopTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.io.ktor.client.mock.jvm)
                implementation(libs.org.jetbrains.kotlin.test.junit)
            }
        }
        named("commonTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.io.ktor.client.mock)
                implementation(libs.org.jetbrains.kotlin.test.common)
                implementation(libs.org.jetbrains.kotlin.test.annotations.common)
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
            }
        }
    }
}
