import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
    id("social.androiddev.code-quality")
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
                implementation(libs.io.insert.koin.core)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "social.androiddev.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Dodo"
            packageVersion = "1.0.0"

            modules("java.sql")

            windows {
                menuGroup = "Dodo"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "ccfe9aed-16ce-42e3-9fd9-92720794b2c2"
            }
        }
    }
}
