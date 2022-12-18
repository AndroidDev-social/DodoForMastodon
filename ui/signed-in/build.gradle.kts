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
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
            }
        }
    }
}
