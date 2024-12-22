plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.eventify"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.eventify"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "org.junit.runners.JUnit5"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.messaging)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.kotlinx.serialization.json.v151)


    //Navigation
    implementation(libs.androidx.navigation.compose)


    // Network API
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Tests
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.engine)

    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation(libs.accompanist.swiperefresh)

    // Fake data generation
    implementation(libs.javafaker)

    // Logging
    implementation(libs.timber)
    implementation(kotlin("reflect"))

    // JWT
    implementation(libs.java.jwt)

    // Firebase
    implementation(libs.firebase.bom)

    // Permissions
    implementation(libs.accompanist.permissions)

    // Splash screen
    implementation(libs.androidx.core.splashscreen)

    // Coil images
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.androidx.activity.ktx)

}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}


kapt {
    correctErrorTypes = true
}
