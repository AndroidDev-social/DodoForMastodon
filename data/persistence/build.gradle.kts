plugins {
    id("multiplatform-setup")
    id("android-setup")
    id("com.squareup.sqldelight")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
            }
        }

        androidMain {
            dependencies {
//                Deps.Squareup.SQLDelight.androidDriver
//                Deps.Squareup.SQLDelight.sqliteDriver
                implementation("io.ktor:ktor-client-okhttp:2.1.3")
                //implementation(Deps.Squareup.SQLDelight.androidDriver)
                //implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }

        desktopMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.sqliteDriver)
            }
        }

        iosMain {
            dependencies {
                implementation(Deps.Squareup.SQLDelight.nativeDriver)
            }
        }
    }
}
