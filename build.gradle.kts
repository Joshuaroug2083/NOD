import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt) apply false
}

val ktlintVersion = libs.versions.ktlint.asProvider().get()
val detektVersion = libs.versions.detekt.get()

allprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    extensions.configure<KtlintExtension> {
        version.set(ktlintVersion)
        filter {
            exclude("**/build/**")
        }
    }
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    extensions.configure<DetektExtension> {
        toolVersion = detektVersion
        buildUponDefaultConfig = true
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        source.setFrom(fileTree("src") { include("**/*.kt") })
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        extensions.configure<KotlinProjectExtension> { jvmToolchain(17) }
    }
    pluginManager.withPlugin("org.jetbrains.kotlin.android") {
        extensions.configure<KotlinProjectExtension> { jvmToolchain(17) }
    }
    pluginManager.withPlugin("com.android.application") {
        extensions.configure<ApplicationExtension> {
            compileSdk = 36
            defaultConfig {
                minSdk = 26
                targetSdk = 36
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            lint {
                warningsAsErrors = true
                // Versions are reviewed as a compatible set; update notices are not code defects.
                informational +=
                    setOf("AndroidGradlePluginVersion", "GradleDependency", "NewerVersionAvailable")
            }
        }
    }
    pluginManager.withPlugin("com.android.library") {
        extensions.configure<LibraryExtension> {
            compileSdk = 36
            defaultConfig {
                minSdk = 26
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            // Library test APKs otherwise default to minSdk and exercise legacy behavior.
            testOptions { targetSdk = 36 }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
            lint {
                warningsAsErrors = true
                informational +=
                    setOf("AndroidGradlePluginVersion", "GradleDependency", "NewerVersionAvailable")
            }
        }
    }
}

tasks.register("qualityCheck") {
    group = "verification"
    description = "Run formatting, static analysis, JVM tests and Android lint."
    dependsOn("ktlintCheck")
    subprojects.forEach { module ->
        dependsOn("${module.path}:ktlintCheck", "${module.path}:detekt")
        module.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
            dependsOn("${module.path}:test")
        }
        module.pluginManager.withPlugin("com.android.library") {
            dependsOn("${module.path}:testDebugUnitTest", "${module.path}:lintDebug")
        }
        module.pluginManager.withPlugin("com.android.application") {
            dependsOn("${module.path}:testDebugUnitTest", "${module.path}:lintDebug")
        }
    }
}
