plugins {
    id("kotlin-multiplatform")
    id("com.android.library")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33
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
                //implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }
        // testing
        named("commonTest") {
            dependencies {
                implementation("com.google.truth:truth:1.1.3")
                implementation("io.ktor:ktor-client-mock-jvm:2.1.1")
            }
        }
    }
}
