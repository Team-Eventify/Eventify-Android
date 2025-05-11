plugins {
    id("feature-convention")
}


android {
    namespace = "com.example.eventify.feature.register.impl"
}

dependencies {
    api(project(":feature:register:api"))
    api(project(":feature:login:api"))
    api(project(":feature:setup:api"))
    implementation(project(":data"))

}