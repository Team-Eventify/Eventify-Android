
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
    // TODO devide between compose and feature plugins
    // Compose
    implementation(libs.androidx.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.accompanist.swiperefresh)
    debugImplementation(libs.androidx.ui.tooling)



    // Hilt

    implementation(project(":uikit"))
    implementation(project(":core:common"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":core:featureManager"))
}