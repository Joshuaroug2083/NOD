plugins { alias(libs.plugins.kotlin.jvm) }

dependencies {
    api(libs.junit)
    api(libs.coroutines.test)
    api(libs.turbine)
}
