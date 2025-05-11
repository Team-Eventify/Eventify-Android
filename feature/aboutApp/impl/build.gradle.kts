
plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.aboutApp.impl"
}

dependencies {
    api(project(":feature:aboutApp:api"))

}
