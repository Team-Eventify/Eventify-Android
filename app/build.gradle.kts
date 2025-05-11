import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)

//
    id("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
//    id("kotlinx-serialization")
//    id("com.google.gms.google-services")
//    id("android-common-convention")
}

fun loadProperties(filename: String): Properties {
    val properties = Properties()
    file(filename).inputStream().use { properties.load(it) }
    return properties
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

    val releaseProperties = loadProperties("../release.properties")
    val debugProperties = loadProperties("../debug.properties")


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField("String", "APPMETRICA_API_KEY", "\"${releaseProperties["appmetrica.api.key"]}\"")
            buildConfigField("String", "API_BASE_URL", "\"${releaseProperties["api.base.url"]}\"")
        }
        debug {
            buildConfigField("String", "APPMETRICA_API_KEY", "\"${debugProperties["appmetrica.api.key"]}\"")
            buildConfigField("String", "API_BASE_URL", "\"${debugProperties["api.base.url"]}\"")
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":domain"))
    implementation(project(":core:featureManager"))

    implementation(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
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
    kapt(libs.hilt.android.compiler) // annotation processor
    implementation(libs.hilt.android) // runtime
    implementation(libs.androidx.runtime)


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
    implementation(project(":uikit"))

}


kapt {
    correctErrorTypes = true
}



