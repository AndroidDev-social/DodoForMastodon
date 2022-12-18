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
                implementation(projects.ui.common)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
            }
        }
    }
}
