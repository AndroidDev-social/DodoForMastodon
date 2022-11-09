plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

val targetSDKVersion: Int by rootProject.extra
val minSDKVersion: Int by rootProject.extra
val compileSDKVersion: Int by rootProject.extra

android {
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
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    sourceSets {
        // shared
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:2.1.3")
                implementation("io.ktor:ktor-client-serialization:2.0.3")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.3")
                implementation("io.ktor:ktor-client-content-negotiation:2.0.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }


        // android
        getByName("androidMain") {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-cio:2.1.3")
                //implementation(Deps.Squareup.SQLDelight.androidDriver)
                //implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }


        // desktop
        getByName("desktopMain") {
            dependencies {
                implementation("io.ktor:ktor-client-cio:2.1.3")
                //implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }


        // iOS
//        val iosX64Main by getting
//        val iosArm64Main by getting
//        val iosSimulatorArm64Main by getting
//        val iosMain by creating {
//            dependsOn(getByName("commonMain"))
//
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//
//            dependencies {
//                implementation("io.ktor:ktor-client-darwin:2.1.3")
//            }
//        }


        // testing
        named("commonTest") {
            dependencies {
                implementation("com.google.truth:truth:1.1.3")
                implementation("io.ktor:ktor-client-mock-jvm:2.1.1")
            }
        }
    }
}
