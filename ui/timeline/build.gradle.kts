plugins {
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

val targetSDKVersion: Int by rootProject.extra
val minSDKVersion: Int by rootProject.extra
val compileSDKVersion: Int by rootProject.extra

android {
    namespace = "social.androiddev.ui.timeline"
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
                implementation(project(":domain:timeline"))
                implementation(project(":ui:common"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
//                implementation("com.arkivanov.decompose:decompose:1.0.0-alpha-05")
//                implementation("com.arkivanov.decompose:extensions-compose-jetbrains:1.0.0-alpha-05")
            }
        }

        named("androidMain") {
            dependencies {
//                dependsOn(commonMain)
                // Workaround for https://github.com/JetBrains/compose-jb/issues/2340
                implementation("androidx.compose.foundation:foundation:1.2.1")
                implementation("androidx.appcompat:appcompat:1.3.0")
                implementation("androidx.core:core-ktx:1.3.1")
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
