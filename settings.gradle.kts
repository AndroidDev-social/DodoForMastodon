enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
    includeBuild("build-logic")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    }
}

rootProject.name="DodoForMastodon"

include(":di")
include(":logging")
include(":app-android")
include(":app-desktop")

include(":ui:timeline")
include(":ui:common")
include(":ui:root")
include(":ui:signed-in")
include(":ui:signed-out")
include(":ui:desktop-webview")

include(":ui:compose-toot")
include(":domain:timeline")
include(":domain:authentication")

include(":data:persistence")
include(":data:network")
include(":data:repository")
