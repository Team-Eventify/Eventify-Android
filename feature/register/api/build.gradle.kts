plugins {
    id("android-common-convention")

}


android {
    namespace = "com.example.eventify.feature.register.api"
}

dependencies {
    implementation(project(":core:common"))


}