buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
        classpath("com.android.tools.build:gradle:7.2.2")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.7.20")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
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
