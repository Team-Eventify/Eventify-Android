plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.profile.impl"
}

dependencies {
    api(project(":feature:profile:api"))
    api(project(":feature:profileEdit:api"))
    api(project(":feature:aboutApp:api"))

}