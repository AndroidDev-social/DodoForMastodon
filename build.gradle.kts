import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

plugins {
    // Suppressed because of https://youtrack.jetbrains.com/issue/KTIJ-19369
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.io.gitlab.arturbosch.detekt.gradle)
}

buildscript {

    extra["targetSDKVersion"] = 33
    extra["compileSDKVersion"] = 33
    extra["minSDKVersion"] = 23

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.org.jetbrains.compose.gradle.plugin)
        classpath(libs.org.jetbrains.kotlin.gradle.plugin)
        classpath(libs.com.android.tools.build.gradle)
        classpath(libs.org.jetbrains.kotlin.serialization)
        classpath(libs.com.squareup.sqldelight.gradle.plugin)
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

val sarifReportMerge by tasks.registering(ReportMergeTask::class) {
    output.set(rootProject.buildDir.resolve("reports/detekt/merged_report.sarif"))
}

subprojects {

    /**
     * Start Configuring Detekt
     */
    coreDetektSetup()

    beforeEvaluate {
        dependencies {
            detektPlugins(libs.io.gitlab.arturbosch.detekt.formatting)
            detektPlugins(libs.com.twitter.compose.rules.detekt)
        }
        filterDetektFromCheckTask()
    }

    /**
     * Workaround for:
     * The Kotlin source set androidAndroidTestRelease was configured but not added to any
     * Kotlin compilation. You can add a source set to a target's compilation by connecting it
     * with the compilation's default source set using 'dependsOn'.
     * See https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#connecting-source-sets
     *
     * Remove log pollution until Android support in KMP improves.
     */
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

/**
 * To run detekt simply:
 * 1. ./gradlew module:detekt for each module
 * 2. ./gradlew detekt for whole project
 */
fun Project.coreDetektSetup() {
    // Apply Plugin to sub-project
    apply(plugin = "io.gitlab.arturbosch.detekt")

    // Configure Detekt
    detekt {
        config = files("$rootDir/config/detekt/detekt.yml")
        buildUponDefaultConfig = true
        ignoredBuildTypes = listOf("release")
        source = files(
            io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_JAVA,
            io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_JAVA,
            io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
            io.gitlab.arturbosch.detekt.extensions.DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN,
            // Kotlin Multiplatform
            "src/commonMain/kotlin",
            "src/androidMain/kotlin",
            "src/iosMain/kotlin",
            "src/jvmMain/kotlin",
            "src/desktopMain/kotlin",
            "src/jsMain/kotlin",
        )
    }

    tasks.withType<Detekt>().configureEach detekt@{
        exclude("**/build/**", "**/generated/**", "**/resources/**")
        basePath = rootProject.projectDir.absolutePath
        autoCorrect = true // Auto corrects common formatting issues
        // Configure reports here
        reports {
            xml.required.set(false)
            txt.required.set(false)
            md.required.set(false)

            html {
                required.set(true)
                outputLocation.set(
                    layout.buildDirectory.file("reports/detekt.html")
                )
            }

            sarif.required.set(true)
        }

        // Merged Report
        finalizedBy(sarifReportMerge)
        sarifReportMerge.configure {
            input.from(this@detekt.sarifReportFile)
        }
    }

    tasks.withType<DetektCreateBaselineTask>().configureEach detekt@{
        exclude("**/build/**", "**/generated/**", "**/resources/**")
        basePath = rootProject.projectDir.absolutePath
    }
}

/**
 * By default Detekt runs when the Gradle :check task is triggered
 * It should be disabled for now until everything has been correctly setup (including CI)
 */
fun Project.filterDetektFromCheckTask() {
    if (tasks.names.contains("check")) {
        tasks.named("check").configure {
            this.setDependsOn(this.dependsOn.filterNot {
                it is TaskProvider<*> && it.name == "detekt"
            })
        }
    }
}
