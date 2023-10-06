import com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask

plugins {
    id("info.offthecob.Library")
    id("com.netflix.dgs.codegen") version "6.0.2"
}

dependencies {
    api("com.fasterxml.jackson.core:jackson-annotations:2.15.2")
}

tasks {
    generateJava {
        schemaPaths = mutableListOf("$projectDir/src/main/resources")
        packageName = "info.offthecob.examples.library"
        generateClient = false
        language = "kotlin"
    }
    build {
        dependsOn("generateJava")
    }
}
