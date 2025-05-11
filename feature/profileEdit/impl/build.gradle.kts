plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.profileEdit.impl"
}

dependencies {
    api(project(":feature:profileEdit:api"))
    implementation(project(":data"))

}