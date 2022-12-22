plugins {
    id("com.android.application")
    id("social.androiddev.android.common")
    kotlin("android")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "social.androiddev.dodo"

    defaultConfig {
        versionCode = 1
        versionName = "1.0"
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
    implementation(libs.androidx.browser)
}
