
plugins {
    id("kotlinx-serialization")
    id("android-common-convention")
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.example.eventify.data"
}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.hilt.android)
    implementation(libs.logging.interceptor)
}