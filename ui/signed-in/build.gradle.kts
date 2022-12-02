plugins {
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("com.diffplug.spotless") version "6.11.0"
    id("kotlin-parcelize")
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

    namespace = "social.androiddev.ui.signed_in"

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

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.ui.common)
                implementation(projects.ui.timeline)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }

        named("androidMain") {
            dependencies {
                // Workaround for https://github.com/JetBrains/compose-jb/issues/2340
                implementation(libs.androidx.compose.foundation)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.core.ktx)
            }
        }

        named("desktopMain") {
            dependencies {
//                dependsOn(commonMain)
                implementation(compose.desktop.common)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
