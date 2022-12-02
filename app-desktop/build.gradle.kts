import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
    id("com.diffplug.spotless") version "6.11.0"
}

spotless {
    kotlin {
        target("src/*/kotlin/**/*.kt")
        ktlint("0.43.2")
        licenseHeaderFile(rootProject.file("copyright.kt"))
    }
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(projects.ui.root)
                implementation(projects.ui.common)
                implementation(libs.com.arkivanov.decompose)
                implementation(libs.com.arkivanov.decompose.extensions.compose.jetbrains)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "social.androiddev.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MastodonCompose"
            packageVersion = "1.0.0"

            modules("java.sql")

            windows {
                menuGroup = "Mastodon Compose"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "ccfe9aed-16ce-42e3-9fd9-92720794b2c2"
            }
        }
    }
}
