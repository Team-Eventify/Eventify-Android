
plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.eventDetail.impl"
}

dependencies {
    api(project(":feature:eventDetail:api"))
    implementation(project(":core:common"))
    implementation(libs.coil3.coil.compose)
    implementation(project(":data"))

}
