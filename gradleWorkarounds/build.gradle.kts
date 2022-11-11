plugins {
    `kotlin-dsl`
    java
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation(libs.org.jetbrains.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins.register("social.androiddev.gradle.workarounds") {
        id = "social.androiddev.gradle.workarounds"
        implementationClass = "social.androiddev.gradle.Workarounds"
    }
}
