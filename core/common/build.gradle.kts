import java.util.Properties

plugins {
   id("core-convention")
    alias(libs.plugins.compose.compiler)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

//fun loadProperties(filename: String): Properties {
//    val properties = Properties()
//    file(filename).inputStream().use { properties.load(it) }
//    return properties
//}

android {
    namespace = "com.example.eventify.core.common"


}

dependencies {

    implementation(libs.androidx.navigation.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.gson)
    implementation(libs.java.jwt)
    implementation(libs.androidx.security.crypto)
    implementation(libs.kotlinx.serialization.json.v151)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}
