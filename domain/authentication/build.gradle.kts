plugins {
    id("social.androiddev.library")
}

android {
    namespace = "social.androiddev.domain.authentication"
}

kotlin {
    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(libs.io.insert.koin.core)
            }
        }
    }
}