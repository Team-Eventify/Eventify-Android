plugins {
    id("android-common-convention")

}


android {
    namespace = "com.example.eventify.feature.profile.api"
}

dependencies {
    implementation(project(":core:common"))

}