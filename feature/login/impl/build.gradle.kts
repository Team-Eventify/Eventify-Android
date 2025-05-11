
plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.loging.impl"
}

dependencies {
    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(project(":feature:login:api"))
    implementation(project(":feature:register:api"))
    implementation(project(":feature:resetPassword:api"))
    implementation(project(":feature:eventFeed:api"))
    implementation(project(":data"))
}
