package social.androiddev.gradle

import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTargetWithSimulatorTests

// see https://youtrack.jetbrains.com/issue/KT-45416/Do-not-use-iPhone-8-simulator-for-Gradle-tests
fun KotlinMultiplatformExtension.overrideAppleDevices() {
    val appleTargets = targets.withType(KotlinNativeTargetWithSimulatorTests::class.java)

    appleTargets.forEach { target ->
        when {
            target.name.startsWith("ios") -> {
                target.testRuns["test"].deviceId = "iPhone 14"
            }
            target.name.startsWith("watchos") -> {
                target.testRuns["test"].deviceId = "Apple Watch Series 7 (45mm)"
            }
            else -> { /* do nothing */ }
        }
    }
}
