/**
 * Convention plugin for non-ui library modules.
 */
plugins {
    id("social.androiddev.library.common")
}

val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

kotlin {
    jvm("desktop")
    android()
    ios()

    sourceSets {
        // shared
        val commonMain by getting

        val androidMain by getting {
            dependsOn(commonMain)
        }

        // testing
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.org.jetbrains.kotlin.test.common)
                implementation(libs.org.jetbrains.kotlin.test.annotations.common)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.org.jetbrains.kotlin.test.junit)
            }
        }
        val desktopTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.org.jetbrains.kotlin.test.junit)
            }
        }
    }
}