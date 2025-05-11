
plugins {
    id("android-common-convention")

}


android {
    namespace = "com.example.eventify.feature.myEvents.api"
}

dependencies {
    implementation(project(":core:common"))


}
