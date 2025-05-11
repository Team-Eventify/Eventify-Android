
plugins {
    id("core-convention")
    id("kotlinx-serialization")
    alias(libs.plugins.compose.compiler)

    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")

}


android {
    namespace = "com.example.eventify.core.featureManager"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}
