plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
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
    namespace = "social.androiddev.data.repository"
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
                implementation(projects.data.network)
                implementation(projects.data.persistence)
                implementation(projects.domain.authentication)
            }
        }

        // android
        getByName("androidMain") {
            dependsOn(commonMain)
            dependencies {}
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

        // desktop
        getByName("desktopMain") {
            dependencies {}
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
