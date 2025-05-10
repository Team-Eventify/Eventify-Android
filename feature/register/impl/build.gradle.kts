plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.register.impl"
}

dependencies {
    api(project(":feature:register:api"))

}