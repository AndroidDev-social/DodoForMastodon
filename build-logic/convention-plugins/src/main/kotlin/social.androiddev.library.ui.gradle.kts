plugins {
    id("social.androiddev.library.common")
    id("org.jetbrains.compose")
}

val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

kotlin {
    jvm("desktop")
    android()

    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }

        // android
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core.ktx)
                // Workaround for https://github.com/JetBrains/compose-jb/issues/2340
                implementation(libs.androidx.compose.foundation)
            }
        }

        // desktop
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}