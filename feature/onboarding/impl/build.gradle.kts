
plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.onboarding.impl"
}

dependencies {
    api(project(":feature:onboarding:api"))
    api(project(":feature:register:api"))

}
