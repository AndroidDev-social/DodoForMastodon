/**
 * Convention plugin to apply common configurations for library modules. Instead of depending on
 * this directly you should depend on `social.androiddev.library` or `social.androiddev.library.ui`.
 */
plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.library")
    id("social.androiddev.android.common")
}

android {
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
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