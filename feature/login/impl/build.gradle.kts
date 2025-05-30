
plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}


android {
    namespace = "com.example.eventify.feature.loging.impl"
}

dependencies {
//    kapt(libs.hilt.android.compiler)

    api(project(":feature:login:api"))
    api(project(":feature:register:api"))
    api(project(":feature:resetPassword:api"))
    api(project(":feature:eventFeed:api"))

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

}
