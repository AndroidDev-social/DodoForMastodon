plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("com.diffplug.spotless")
}

val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

spotless {
    kotlin {
        target("src/*/kotlin/**/*.kt")
        ktlint("0.43.2")
        licenseHeaderFile(File(rootDir, "copyright.txt"))
    }
}

// Workaround for:
//
// The Kotlin source set androidAndroidTestRelease was configured but not added to any
// Kotlin compilation. You can add a source set to a target's compilation by connecting it
// with the compilation's default source set using 'dependsOn'.
// See https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#connecting-source-sets
//
// Remove log pollution until Android support in KMP improves.
//
afterEvaluate {
    project.extensions.findByType<org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension>()?.let { ext ->
        ext.sourceSets.removeAll { sourceSet ->
            setOf(
                "androidAndroidTestRelease",
                "androidTestFixtures",
                "androidTestFixturesDebug",
                "androidTestFixturesRelease",
            ).contains(sourceSet.name)
        }
    }
}