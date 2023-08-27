plugins {
    id("info.offthecob.Base")
    application
}

application {
    mainClass.set("MainKt")
}

dependencies {
    implementation(platform(libs.offthecob.platform))
    implementation("info.offthecob.platform:common")
    implementation(libs.bundles.guice)
    implementation(libs.bundles.logging)
    implementation(libs.unirest)

    testImplementation(libs.bundles.groovy)
    testImplementation(libs.bundles.spock)
}

allOpen {
    annotation("info.offthecob.common.OpenForTesting")
}
