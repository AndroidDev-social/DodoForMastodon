import social.androiddev.gradle.isIdea

plugins {
    id("kotlin-multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

val targetSDKVersion: Int by rootProject.extra
val minSDKVersion: Int by rootProject.extra
val compileSDKVersion: Int by rootProject.extra

android {
    namespace = "social.androiddev.ui.timeline"
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
                implementation(projects.domain.timeline)
                implementation(projects.ui.common)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val commonTest by getting

        // android
        val androidMain by getting {
            dependencies {
                // Workaround for https://github.com/JetBrains/compose-jb/issues/2340
                implementation(libs.androidx.compose.foundation)
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.core.ktx)
            }
        }

        if (!isIdea()) {
            val androidAndroidTestRelease by getting
            val androidAndroidTest by getting {
                dependsOn(androidAndroidTestRelease)
            }
            val androidTestFixturesDebug by getting
            val androidTestFixturesRelease by getting

            val androidTestFixtures by getting {
                dependsOn(androidTestFixturesDebug)
                dependsOn(androidTestFixturesRelease)
            }

            val androidTest by getting {
                dependsOn(androidTestFixtures)
            }
        }
        val androidTest by getting

        // desktop
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
        val desktopTest by getting
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}
