plugins {
    id("social.androiddev.library")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization")
    id("kotlinx-atomicfu")
}

sqldelight {
    database("AuthenticationDatabase") {
        packageName = "social.androiddev.common.persistence"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "social.androiddev.common.persistence"
}

kotlin {
    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(libs.multiplatform.settings)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.io.insert.koin.core)
            }
        }

        // android
        val androidMain by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.android.driver)
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
            }
        }

        // desktop
        val desktopMain by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
            }
        }

        // iOS
        val iosMain by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.native.driver)
            }
        }

        // testing
        val commonTest by getting {
            dependencies {
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
                implementation(libs.org.xerial.sqlite.jdbc)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.android.driver)
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
                implementation(libs.org.xerial.sqlite.jdbc)
            }
        }
        val desktopTest by getting {
            dependencies {
                implementation(libs.com.squareup.sqldelight.sqlite.driver)
                implementation(libs.org.xerial.sqlite.jdbc)
            }
        }
        val iosTest by getting {
            dependencies {
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
                implementation(libs.com.squareup.sqldelight.native.driver)
            }
        }
    }
}
