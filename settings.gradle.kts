pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Eventify"
include(":app")
include(
    ":uikit"
)

include(
    ":core:common",
    ":core:featureManager",
)

include(
    ":data",
)

include(
    ":domain",
)


include(
    ":feature:eventFeed:impl",
    ":feature:eventFeed:api",
    ":feature:eventDetail:impl",
    ":feature:eventDetail:api",
    ":feature:register:impl",
    ":feature:register:api",
    ":feature:login:impl",
    ":feature:login:api",
    ":feature:setup:api",
    ":feature:setup:impl",
    ":feature:resetPassword:api",
    ":feature:resetPassword:impl",
    ":feature:onboarding:api",
    ":feature:onboarding:impl",
    ":feature:myEvents:api",
    ":feature:myEvents:impl",
    ":feature:aboutApp:api",
    ":feature:aboutApp:impl",
    ":feature:aboutApp:api",
    ":feature:aboutApp:impl",
    ":feature:profile:api",
    ":feature:profile:impl",
    ":feature:profileEdit:api",
    ":feature:profileEdit:impl",
    ":feature:search:api",
    ":feature:search:impl",
    ":feature:searchResult:api",
    ":feature:searchResult:impl",
    ":feature:setup:api",
    ":feature:setup:impl",

)

 