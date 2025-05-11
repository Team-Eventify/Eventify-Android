
plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.resetPassword.impl"
}

dependencies {
    api(project(":feature:resetPassword:api"))

}
