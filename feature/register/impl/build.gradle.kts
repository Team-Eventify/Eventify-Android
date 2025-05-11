plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.register.impl"
}

dependencies {
    api(project(":feature:register:api"))
    api(project(":feature:login:api"))
    api(project(":feature:setup:api"))
    implementation(project(":data"))

}