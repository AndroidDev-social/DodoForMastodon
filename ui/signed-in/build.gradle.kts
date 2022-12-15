plugins {
    id("social.androiddev.library.ui")
    id("kotlin-parcelize")
}

android {
    namespace = "social.androiddev.ui.signed_in"
}
dependencies {
    implementation(project(mapOf("path" to ":data:persistence")))
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.ui.common)
                implementation(projects.ui.timeline)
                implementation(projects.data.persistence)
                implementation(projects.data.repository)
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
