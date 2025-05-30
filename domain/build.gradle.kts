plugins {
    id("core-convention")
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}


android {
    namespace = "com.example.eventify.domain"
}

dependencies {
    implementation(project(":data"))
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)

    implementation(project(":core:common"))

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}