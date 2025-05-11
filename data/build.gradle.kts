
plugins {
    id("kotlinx-serialization")
    id("android-common-convention")

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

}