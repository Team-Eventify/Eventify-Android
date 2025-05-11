
plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    buildFeatures {
        buildConfig = true
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

    defaultConfig {
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}