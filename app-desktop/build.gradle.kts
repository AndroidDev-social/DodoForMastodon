import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(projects.ui.timeline)
                implementation(projects.ui.common)
                implementation(projects.ui.welcome)
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
