buildscript {

    extra["targetSDKVersion"] = 33
    extra["compileSDKVersion"] = 33
    extra["minSDKVersion"] = 23

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.org.jetbrains.compose.gradle.plugin)
        classpath(libs.org.jetbrains.kotlin.gradle.plugin)
        classpath(libs.com.android.tools.build.gradle)
        classpath(libs.org.jetbrains.kotlin.serialization)
        classpath(libs.com.squareup.sqldelight.gradle.plugin)
        classpath(libs.dev.icerock.moko.resources.generator)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
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
subprojects {
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
}
