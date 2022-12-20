plugins {
    id("social.androiddev.library")
    kotlin("plugin.serialization")
}

android {
    namespace = "social.androiddev.common.repository"
}

kotlin {
    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation(projects.data.network)
                implementation(projects.data.persistence)
                implementation(projects.domain.authentication)
                implementation(projects.domain.timeline)
                implementation(libs.io.insert.koin.core)
                implementation(libs.kotlinx.coroutines.core)
                //temp until we map to UI models
                api(libs.store)
                implementation(libs.com.squareup.sqldelight.coroutines.extensions)
            }
        }
        // testing
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.org.jetbrains.kotlin.test.junit)
            }
        }
        val androidMain by getting {
            dependencies {
                api (libs.org.jetbrains.kotlinx.atomicfu)
            }
        }
        val desktopTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.org.jetbrains.kotlin.test.junit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.org.jetbrains.kotlin.test.common)
                implementation(libs.org.jetbrains.kotlin.test.annotations.common)
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
            }
        }



        // android
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api ("org.jetbrains.kotlinx:atomicfu:0.18.5")
            }
        }
    }
}