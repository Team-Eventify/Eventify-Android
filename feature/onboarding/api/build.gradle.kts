import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("com.google.gms.google-services")
}


android {
    namespace = "com.example.eventify.feature.onboarding.api"
}

dependencies {


}
