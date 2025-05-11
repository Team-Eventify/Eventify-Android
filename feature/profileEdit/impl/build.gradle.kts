plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.profileEdit.impl"
}

dependencies {
    api(project(":feature:profileEdit:api"))
    implementation(project(":data"))

}