plugins {
    id("info.offthecob.Service")
}

dependencies {
    implementation(platform(libs.offthecob.platform))
    implementation("info.offthecob.platform:common")
    implementation(libs.bundles.guice)
    implementation(libs.bundles.logging)
    implementation(libs.javalin)

    testImplementation(libs.bundles.groovy)
    testImplementation(libs.bundles.spock)
}
