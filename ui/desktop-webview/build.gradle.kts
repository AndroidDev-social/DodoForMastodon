plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("social.androiddev.codequality")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

dependencies {
    implementation(compose.desktop.common)
}

javafx {
    version = "19"
    modules(
        "javafx.base",
        "javafx.controls",
        "javafx.graphics",
        "javafx.swing",
        "javafx.web",
        "javafx.media"
    )
}
