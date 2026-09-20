plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android { namespace = "dev.nod.transport.nearby" }

dependencies {
    implementation(project(":transport:api"))
    implementation(libs.nearby)
}
