// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("com.google.dagger.hilt.android") version "2.56.2" apply false

    kotlin("plugin.serialization") version "1.9.21"
    id("com.google.devtools.ksp") version "2.1.20-2.0.0" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false

    id("android-common-convention") apply false
    id("core-convention") apply false
    id("feature-convention") apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
}