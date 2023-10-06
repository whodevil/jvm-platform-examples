plugins {
    id("info.offthecob.Service")
}

dependencies {
    implementation(project(":library"))
    implementation(platform(libs.offthecob.platform))
    implementation("info.offthecob.platform:common")
    implementation(libs.bundles.guice)
    implementation(libs.bundles.logging)
    implementation(libs.javalin)
    implementation(libs.gson)
    implementation(libs.graphql)
    implementation(libs.commons.io)

    testImplementation(libs.bundles.groovy)
    testImplementation(libs.bundles.spock)
}
