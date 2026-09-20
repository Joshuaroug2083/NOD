plugins { alias(libs.plugins.kotlin.jvm) }

dependencies {
    api(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:protocol"))
    implementation(project(":transport:api"))
    testImplementation(project(":core:testing"))
}
