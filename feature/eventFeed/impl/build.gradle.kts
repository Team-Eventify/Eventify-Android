plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.eventFeed.impl"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.accompanist.swiperefresh)


    api(project(":feature:eventFeed:api"))
    api(project(":feature:eventDetail:api"))

}