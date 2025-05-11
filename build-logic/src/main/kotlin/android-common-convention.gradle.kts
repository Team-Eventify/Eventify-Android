
plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 35

    defaultConfig {
//        applicationId = "com.example.eventify"
        minSdk = 29
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "org.junit.runners.JUnit5"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }


}