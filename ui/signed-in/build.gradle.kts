plugins {
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("social.androiddev.code-quality")
    id("kotlin-parcelize")
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
dependencies {
    implementation(project(mapOf("path" to ":data:persistence")))
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
                implementation(projects.data.persistence)
                implementation(projects.data.repository)
                implementation(libs.io.insert.koin.core)
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
