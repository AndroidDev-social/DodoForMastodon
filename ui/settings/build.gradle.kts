plugins {
    id("social.androiddev.library.ui")
}

android {
    namespace = "social.androiddev.ui.settings"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.ui.common)
                implementation(projects.data.persistence)
                implementation(projects.data.repository)
                implementation(libs.io.insert.koin.core)
            }
        }
    }
}
