plugins {
    id("info.offthecob.Settings") version "1.0.9"
}

rootProject.name = "jvm-platform-examples"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from("info.offthecob.platform:catalog:1.0.9")
        }
    }
}
