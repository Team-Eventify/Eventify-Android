
plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.onboarding.impl"
}

dependencies {
    api(project(":feature:onboarding:api"))

}
