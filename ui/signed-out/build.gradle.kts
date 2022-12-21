plugins {
    id("social.androiddev.library.ui")
    id("kotlin-parcelize")
    id("com.google.osdetector")
}

android {
    namespace = "social.androiddev.ui.signed_out"
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.ui.common)
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
                //FIXME : Find the right way to use javafx in kotlin multiplatform project with android plugin
                // https://stackoverflow.com/questions/73187027/use-javafx-in-kotlin-multiplatform
                // As JavaFX have platform-specific dependencies, we need to add them manually
                val fxSuffix = when (osdetector.classifier) {
                    "linux-x86_64" -> "linux"
                    "linux-aarch_64" -> "linux-aarch64"
                    "windows-x86_64" -> "win"
                    "osx-x86_64" -> "mac"
                    "osx-aarch_64" -> "mac-aarch64"
                    else -> throw IllegalStateException("Unknown OS: ${osdetector.classifier}")
                }

                implementation("org.openjfx:javafx-base:19:${fxSuffix}")
                implementation("org.openjfx:javafx-graphics:19:${fxSuffix}")
                implementation("org.openjfx:javafx-controls:19:${fxSuffix}")
                implementation("org.openjfx:javafx-web:19:${fxSuffix}")
                implementation("org.openjfx:javafx-swing:19:${fxSuffix}")
                implementation("org.openjfx:javafx-media:19:${fxSuffix}")

            }
        }
    }
}