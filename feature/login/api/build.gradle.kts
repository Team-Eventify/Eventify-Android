
plugins {
    id("android-common-convention")
}


android {
    namespace = "com.example.eventify.feature.loging.api"
}

dependencies {
    implementation(project(":core:common"))
}

