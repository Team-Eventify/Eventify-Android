plugins {
    id("feature-convention")
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}


android {
    namespace = "com.example.eventify.feature.register.impl"
}

dependencies {
    api(project(":feature:register:api"))
    api(project(":feature:login:api"))
    api(project(":feature:setup:api"))

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

}