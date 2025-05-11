
plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.resetPassword.impl"
}

dependencies {
    api(project(":feature:resetPassword:api"))

}
