buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.org.jetbrains.compose.gradle.plugin)
        classpath(libs.org.jetbrains.kotlin.gradle.plugin)
        classpath(libs.com.android.tools.build.gradle)
        classpath(libs.org.jetbrains.kotlin.serialization.plugin)
        classpath(libs.com.squareup.sqldelight.gradle.plugin)
        classpath(libs.org.jetbrains.kotlinx.atomicfu.plugin)
    }
}