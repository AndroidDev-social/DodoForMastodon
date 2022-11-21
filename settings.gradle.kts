enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name="MastodonCompose"

include(":app-android")
include(":app-desktop")
//include(":app-iOS")

include(":ui:timeline")
include(":ui:welcome")
//include(":ui:messages")
//include(":ui:notifications")
//include(":ui:search")
//include(":ui:settings")
include(":ui:common")

include(":domain:timeline")
include(":domain:authentication")

//include(":data-timeline")
include(":data:persistence")
include(":data:network")
include(":data:repository")
