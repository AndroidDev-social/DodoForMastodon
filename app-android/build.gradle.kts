plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
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
    namespace = "social.androiddev.dodo"
    compileSdk = compileSDKVersion

    defaultConfig {
        minSdk = minSDKVersion
        targetSdk = targetSDKVersion
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(projects.ui.root)
    implementation(projects.ui.common)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(projects.di)
    implementation(libs.io.insert.koin.core)
    implementation(libs.io.insert.koin.android)
    implementation(libs.com.arkivanov.decompose)
    implementation(libs.com.arkivanov.decompose.extensions.compose.jetbrains)
}
