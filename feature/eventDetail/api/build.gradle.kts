
plugins {
    id("android-common-convention")
}


android {
    namespace = "com.example.eventify.feature.eventDetail.api"
}

dependencies {
    implementation(project(":core:common"))
}
