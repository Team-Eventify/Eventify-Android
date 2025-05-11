plugins {
    id("core-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.domain"
}

dependencies {
    implementation(project(":data"))
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(project(":core:common"))
}