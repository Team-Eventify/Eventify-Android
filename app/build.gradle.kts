import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)

//
//    id("kotlin-kapt")

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}



android {
    namespace = "com.example.eventify"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.eventify"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.8.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:featureManager"))
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.analytics)
    implementation(libs.push.provider.firebase)
    implementation(libs.push)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3)


    implementation(libs.androidx.runtime)

    // HIlt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


    implementation(project(":feature:login:impl"))
    implementation(project(":feature:register:impl"))
    implementation(project(":feature:onboarding:impl"))
    implementation(project(":feature:setup:impl"))
    implementation(project(":feature:eventFeed:impl"))
    implementation(project(":feature:eventDetail:impl"))
    implementation(project(":feature:myEvents:impl"))
    implementation(project(":feature:profile:impl"))
    implementation(project(":feature:profileEdit:impl"))
    implementation(project(":feature:aboutApp:impl"))
    implementation(project(":feature:resetPassword:impl"))
    implementation(project(":feature:search:impl"))
    implementation(project(":feature:searchResult:impl"))
    implementation(project(":uikit"))



}




