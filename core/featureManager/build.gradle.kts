
plugins {
    id("core-convention")
    id("kotlinx-serialization")
}


android {
    namespace = "com.example.eventify.core.featureManager"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
}
