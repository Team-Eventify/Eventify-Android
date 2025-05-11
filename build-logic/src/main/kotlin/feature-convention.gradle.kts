
plugins {
    id("android-common-convention")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.8.1"
    }
}

dependencies {
    // Compose
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.accompanist.swiperefresh)


    // Hilt
    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(project(":uikit"))
    implementation(project(":core:common"))
    implementation(project(":domain"))
    implementation(project(":core:featureManager"))
}