plugins {
    id("social.androiddev.gradle.workarounds")
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
