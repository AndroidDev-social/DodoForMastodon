plugins {
    id("social.androiddev.library")
}

android {
    namespace = "social.androiddev.domain.timeline"
}

kotlin {
    jvm("desktop")
    android()
    iosX64()
    iosArm64()

    sourceSets {
        // shared

        val commonMain by getting {
            dependencies {
                api(libs.store)
            }
        }



    }
}
