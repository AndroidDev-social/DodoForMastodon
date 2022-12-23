/**
 * Convention plugin to apply android configuration that's common across both library and app
 * modules.
 */
plugins {
    id("social.androiddev.codequality")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 24
    }

    // targetSdk is in a different interface for library and application projects
    when (this) {
        is com.android.build.api.dsl.ApplicationBaseFlavor -> {
            targetSdk = 33
        }
        is com.android.build.api.dsl.LibraryBaseFlavor -> {
            targetSdk = 33
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

fun Project.android(configure: com.android.build.api.dsl.CommonExtension<*, *, *, *>.() -> Unit) {
    extensions.configure("android", configure)
}
