enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RickAndMorty"
include(":androidApp")
include(":app")

include(":core:common:data")
include(":core:common:domain")
include(":core:common:presentation")
include(":core:designsystem")

include(":feature:details:presentation")
include(":feature:home:presentation")
include(":feature:splash:presentation")
