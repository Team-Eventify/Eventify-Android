
plugins {
    id("core-convention")
}


android {
    namespace = "com.example.eventify.core.common"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
}
