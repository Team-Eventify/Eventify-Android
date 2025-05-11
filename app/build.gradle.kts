import java.util.Properties

plugins {
//    alias(libs.plugins.androidApplication)
//    alias(libs.plugins.jetbrainsKotlinAndroid)
//
//    id("kotlin-kapt")
//    id("com.google.dagger.hilt.android")
//    id("kotlinx-serialization")
//    id("com.google.gms.google-services")
    id("android-common-convention")
}

fun loadProperties(filename: String): Properties {
    val properties = Properties()
    file(filename).inputStream().use { properties.load(it) }
    return properties
}

android {
    namespace = "com.example.eventify"
//    compileSdk = 35
//
//    defaultConfig {
//        applicationId = "com.example.eventify"
//        minSdk = 29
//        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"
//
//        testInstrumentationRunner = "org.junit.runners.JUnit5"
//        vectorDrawables {
//            useSupportLibrary = true
//        }
//    }
//
//    val releaseProperties = loadProperties("../release.properties")
//    val debugProperties = loadProperties("../debug.properties")
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//            signingConfig = signingConfigs.getByName("debug")
//
//            buildConfigField("String", "APPMETRICA_API_KEY", "\"${releaseProperties["appmetrica.api.key"]}\"")
//            buildConfigField("String", "API_BASE_URL", "\"${releaseProperties["api.base.url"]}\"")
//        }
//        debug {
//            buildConfigField("String", "APPMETRICA_API_KEY", "\"${debugProperties["appmetrica.api.key"]}\"")
//            buildConfigField("String", "API_BASE_URL", "\"${debugProperties["api.base.url"]}\"")
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    buildFeatures {
//        compose = true
//        buildConfig = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.5.7"
//    }
//    packaging {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }

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


//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.androidx.ui)
//    implementation(libs.androidx.ui.graphics)
//    implementation(libs.androidx.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    testImplementation(libs.junit.jupiter)
//    testRuntimeOnly(libs.junit.jupiter.engine)
//
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.androidx.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//    implementation(libs.kotlinx.serialization.json.v151)
//
//
//    //Navigation
//    implementation(libs.androidx.navigation.compose)
//
//
//    // Network API
//    implementation(libs.retrofit)
//    implementation(libs.okhttp)
//    implementation(libs.converter.gson)
//    implementation(libs.logging.interceptor)
//
//    // DI
//    implementation(libs.hilt.android)
//    kapt(libs.hilt.android.compiler)
//    implementation(libs.androidx.hilt.navigation.compose)
//
//    // Tests
//    testImplementation(libs.junit)
//
//    testImplementation(libs.mockk)
//    testImplementation(libs.kotlinx.coroutines.test)
//    testImplementation(libs.truth)
//    testImplementation(libs.byte.buddy)
//
//    coreLibraryDesugaring(libs.desugar.jdk.libs)
//    implementation(libs.accompanist.swiperefresh)
//
//    // Fake data generation
//    implementation(libs.javafaker)
//
//    // Logging
//    implementation(libs.timber)
//    implementation(kotlin("reflect"))
//
//    // JWT
//    implementation(libs.java.jwt)
//
//    // Firebase
//    implementation(libs.firebase.bom)
//
//    // Permissions
//    implementation(libs.accompanist.permissions)
//
//    // Splash screen
//    implementation(libs.androidx.core.splashscreen)
//
//    // Coil images
//    implementation(libs.coil3.coil.compose)
//    implementation(libs.coil.network.okhttp)
//
//    implementation(libs.androidx.activity.ktx)
//
//    // Credential Management
//    implementation(libs.androidx.credentials)
//    implementation(libs.androidx.credentials.play.services.auth)
//
//    implementation(libs.androidx.foundation)
//
//    // Yandex Appmetrica
//    implementation(libs.analytics)
//    implementation(libs.firebase.messaging)
//    implementation(libs.push)
//    implementation(libs.androidx.legacy.support.v4)
//    implementation(libs.push.provider.firebase)
//
//    implementation(libs.play.services.base)
//
//    implementation(libs.androidx.security.crypto)

}

//tasks.withType<Test>().configureEach {
//    useJUnitPlatform()
//}


//kapt {
//    correctErrorTypes = true
//}



