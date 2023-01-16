plugins {
    id("social.androiddev.library")
}

android {
    namespace = "social.androiddev.logging"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.io.github.aakira.napier)
                implementation(libs.io.insert.koin.core)
            }
        }
    }
}
