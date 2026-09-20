pluginManagement {
    repositories {
        google()
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

rootProject.name = "Nod"
include(
    ":app",
    ":core:model",
    ":core:common",
    ":core:protocol",
    ":core:testing",
    ":domain",
    ":transport:api",
    ":transport:nearby",
    ":feature:spike"
)
