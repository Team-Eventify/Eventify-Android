plugins {
    id("android-common-convention")
}


android {
    namespace = "com.example.eventify.feature.eventFeed.api"
}

dependencies {
    implementation(project(":core:common"))


}