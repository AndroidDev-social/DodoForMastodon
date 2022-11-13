plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
//    id("com.squareup.sqldelight")
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
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
            }
        }


        // android
        getByName("androidMain") {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.com.squareup.sqldelight.android.driver)
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
            }
        }


        // desktop
        getByName("desktopMain") {
            dependencies {
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
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
                implementation(libs.com.squareup.sqldelight.native.driver)
            }
        }


        // testing
        named("commonTest") {
            dependencies {
                implementation(libs.org.jetbrains.kotlin.test.common)
                implementation(libs.org.jetbrains.kotlin.test.annotations.common)
            }
        }
    }
}
