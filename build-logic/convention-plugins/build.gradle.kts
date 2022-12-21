plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.com.diffplug.spotless.gradle.plugin)
    implementation(libs.org.jetbrains.kotlin.gradle.plugin)
    implementation(libs.org.jetbrains.compose.gradle.plugin)
    implementation(libs.com.android.tools.build.gradle)
    // hack to access version catalogue https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}