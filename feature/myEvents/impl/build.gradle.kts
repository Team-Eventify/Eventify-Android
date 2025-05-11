
plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.myEvents.impl"
}

dependencies {
    api(project(":feature:myEvents:api"))

    api(project(":feature:eventFeed:api"))
    api(project(":feature:eventDetail:api"))

}
