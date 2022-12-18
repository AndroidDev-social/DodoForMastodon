plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
    id("kotlin-parcelize")
}

android {
    namespace = "social.androiddev.dodo"

    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
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
