plugins {
    id("social.androiddev.library.ui")
    id("kotlin-parcelize")
}

android {
    namespace = "social.androiddev.common"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.data.network)
                api(libs.com.arkivanov.decompose)
                api(libs.com.arkivanov.decompose.extensions.compose.jetbrains)
            }
        }
    }
}
