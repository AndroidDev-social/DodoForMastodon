plugins {
    id("social.androiddev.library.ui")
    id("kotlin-parcelize")
}

android {
    namespace = "social.androiddev.ui.signed_out"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.ui.common)
                implementation(projects.di)
                implementation(projects.domain.authentication)
                implementation(libs.io.insert.koin.core)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.browser)
                implementation(libs.androidx.activity.compose)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(projects.ui.desktopWebview)

            }
        }
    }

}