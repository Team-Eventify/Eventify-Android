
plugins {
    id("android-common-convention")
    alias(libs.plugins.compose.compiler)

}


android {
    namespace = "com.example.eventify.feature.setup.api"
}

dependencies {
    implementation(project(":core:common"))


}
