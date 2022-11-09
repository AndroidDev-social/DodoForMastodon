plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
}

val targetSDKVersion: Int by rootProject.extra
val minSDKVersion: Int by rootProject.extra
val compileSDKVersion: Int by rootProject.extra

android {
    namespace = "social.androiddev.domain.timeline"
    compileSdk = compileSDKVersion

    defaultConfig {
        minSdk = minSDKVersion
        targetSdk = targetSDKVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}

kotlin {
    jvm("desktop")
    android()

    sourceSets {
        // shared

        val commonMain by getting {
            dependencies {
                implementation(project(":data:network"))
            }
        }


        // android
        getByName("androidMain") {
            dependsOn(commonMain)
            dependencies {
            }
        }


        // desktop
        getByName("desktopMain") {
            dependencies {
            }
        }
        // testing
        named("commonTest") {
            dependencies {
                implementation(libs.com.google.truth)
            }
        }
    }
}
