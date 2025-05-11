
plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.eventDetail.impl"
}

dependencies {
    api(project(":feature:eventDetail:api"))
    implementation(project(":core:common"))
    implementation(libs.coil3.coil.compose)

}
