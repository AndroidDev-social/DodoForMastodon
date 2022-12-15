plugins {
    id("social.androiddev.library.ui")
}

android {
    namespace = "social.androiddev.ui.timeline"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.domain.timeline)
                implementation(projects.data.persistence)
                implementation(projects.data.repository)
                implementation(projects.ui.common)
                implementation(libs.io.insert.koin.core)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
            }
        }
    }
}
