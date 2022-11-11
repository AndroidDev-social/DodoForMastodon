import social.androiddev.gradle.overrideAppleDevices
import social.androiddev.gradle.isIdea

plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

val targetSDKVersion: Int by rootProject.extra
val minSDKVersion: Int by rootProject.extra
val compileSDKVersion: Int by rootProject.extra

android {
    namespace = "social.androiddev.common.persistence"
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
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(libs.org.jetbrains.kotlin.test.common)
                implementation(libs.org.jetbrains.kotlin.test.annotations.common)
            }
        }

        // android
        val androidMain by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.android.driver)
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
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
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
            }
        }

        val desktopTest by getting


        // iOS
        val iosMain by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.native.driver)
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
