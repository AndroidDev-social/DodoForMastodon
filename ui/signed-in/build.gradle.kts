plugins {
    id("social.androiddev.library.ui")
    id("kotlin-parcelize")
}

android {
    namespace = "social.androiddev.ui.signed_in"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.ui.common)
                implementation(projects.ui.timeline)
                implementation(projects.data.persistence)
                implementation(projects.data.repository)
                implementation(projects.domain.timeline)
                implementation(libs.io.insert.koin.core)
                implementation(projects.data.persistence)
                implementation(projects.data.repository)
                implementation(projects.domain.timeline)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
            }
        }
    }
}
