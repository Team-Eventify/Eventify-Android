plugins {
    id("android-common-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.profileEdit.api"
}

dependencies {
    implementation(project(":core:common"))

}