plugins {
    id("social.androiddev.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "social.androiddev.common.repository"
}

kotlin {
    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(projects.data.network)
                implementation(projects.data.persistence)
                implementation(projects.domain.authentication)
                implementation(libs.io.insert.koin.core)
                implementation(libs.kotlinx.coroutines.core)
            }
        }

        // testing
        val commonTest by getting {
            dependencies {
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
            }
        }
    }
}