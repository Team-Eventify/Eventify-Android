
plugins {
    id("kotlinx-serialization")
    id("android-common-convention")
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")

}

android {
    namespace = "com.example.eventify.data"

    defaultConfig {
        properties("../release.properties") {
            buildConfigField("String", "API_BASE_URL", "\"${this["api.base.url"]}\"")
        }
    }

}

dependencies {
    implementation(project(":core:common"))

    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

}