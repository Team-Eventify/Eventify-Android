import java.util.Properties

plugins {
    id("android-common-convention")

}


android {
    namespace = "com.example.eventify.feature.onboarding.api"
}

dependencies {
    implementation(project(":core:common"))


}
