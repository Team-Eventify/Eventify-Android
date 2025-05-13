plugins {
    id("android-common-convention")
    alias(libs.plugins.compose.compiler)
}


android {
    namespace = "com.example.eventify.uikit"
}

dependencies {
    // Compose
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil3.coil.compose)
    implementation(libs.accompanist.swiperefresh)

}
