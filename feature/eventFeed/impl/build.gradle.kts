plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.eventFeed.impl"
}

dependencies {
    implementation(libs.coil.compose)

    api(project(":feature:eventFeed:api"))
    api(project(":feature:eventDetail:api"))

}