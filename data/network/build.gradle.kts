import social.androiddev.gradle.overrideAppleDevices
import social.androiddev.gradle.isIdea

plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    alias(libs.plugins.test.resources)
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
    ios()
    iosSimulatorArm64()
    overrideAppleDevices()

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
        val commonTest by getting {
            dependencies {

                implementation(kotlin("test"))
                implementation(libs.io.ktor.client.mock)
                implementation(libs.org.jetbrains.kotlin.test.common)
                implementation(libs.org.jetbrains.kotlin.test.annotations.common)
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
                implementation(libs.com.goncalossilva.test.resources)
            }
        }


        // android
        val androidMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.cio)
            }
        }
        if (!isIdea()) {
            val androidAndroidTestRelease by getting
            val androidAndroidTest by getting {
                dependsOn(androidAndroidTestRelease)
            }
            val androidTestFixturesDebug by getting
            val androidTestFixturesRelease by getting

            val androidTestFixtures by getting {
                dependsOn(androidTestFixturesDebug)
                dependsOn(androidTestFixturesRelease)
            }

            val androidTest by getting {
                dependsOn(androidTestFixtures)
            }
        }
        val androidTest by getting


        // desktop
        val desktopMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.cio)
            }
        }
        val desktopTest by getting


        // iOS
        val iosMain by getting {
            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }
    }
}
