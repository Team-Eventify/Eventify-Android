plugins {
    id("kotlinx-serialization")
    id("core-convention")
}

android {
    namespace = "com.example.eventify.data"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
}