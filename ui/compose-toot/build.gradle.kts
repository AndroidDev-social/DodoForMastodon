plugins {
    id("social.androiddev.library.ui")
    id("social.androiddev.codequality")
}

android {
    namespace = "social.androiddev.ui.composetoot"
}

kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.domain.timeline)
                implementation(projects.ui.common)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(libs.io.insert.koin.core)

            }
        }

    }
}
