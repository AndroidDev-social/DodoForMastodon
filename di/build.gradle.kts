plugins {
    id("social.androiddev.library")
}

android {
    namespace = "social.androiddev.di"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.data.network)
                implementation(projects.data.persistence)
                implementation(projects.data.repository)
                implementation(projects.domain.authentication)
                implementation(libs.io.insert.koin.core)
            }
        }
    }
}
