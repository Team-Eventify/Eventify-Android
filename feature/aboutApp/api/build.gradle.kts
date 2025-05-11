
plugins {
    id("android-common-convention")

}


android {
    namespace = "com.example.eventify.feature.aboutApp.api"
}

dependencies {
    implementation(project(":core:common"))
}
