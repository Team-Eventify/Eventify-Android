import java.util.Properties

plugins {
    id("android-common-convention")

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


}
