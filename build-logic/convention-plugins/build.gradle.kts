plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("CodeQuality") {
            id = "social.androiddev.code-quality"
            implementationClass = "social.androiddev.plugins.CodeQualityPlugin"
        }
    }
}

dependencies {
    implementation(libs.com.diffplug.spotless.gradle.plugin)
}