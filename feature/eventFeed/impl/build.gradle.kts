plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}


android {
    namespace = "com.example.eventify.feature.eventFeed.impl"
}

dependencies {
    implementation(libs.coil3.coil.compose)

    api(project(":feature:eventFeed:api"))
    api(project(":feature:eventDetail:api"))

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

}