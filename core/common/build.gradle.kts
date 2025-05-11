
plugins {
   id("core-convention")
}


android {
    namespace = "com.example.eventify.core.common"
}

dependencies {

    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.gson)
    implementation(libs.java.jwt)
    implementation(libs.androidx.security.crypto)
}
