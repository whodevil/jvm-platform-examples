plugins {
    id("info.offthecob.Base")
    application
}

application {
    mainClass.set("MainKt")
}

dependencies {
    implementation(libs.bundles.guice)
    implementation(libs.bundles.logging)

    testImplementation(libs.bundles.groovy)
    testImplementation(libs.bundles.spock)
}
