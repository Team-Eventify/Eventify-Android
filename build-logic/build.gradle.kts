plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    api(libs.gradleplugin.kotlin)
    implementation(libs.gradle.plugin.android.build.tools)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
