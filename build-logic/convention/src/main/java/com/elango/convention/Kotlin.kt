package com.elango.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("projectCompileSdkVersion").get().toString().toInt()
        defaultConfig.apply {
            minSdk = libs.findVersion("projectMinSdkVersion").get().toString().toInt()
        }
        compileOptions.run {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        dependencies {
            "coreLibraryDesugaring"(libs.findLibrary("desugar.jdk.libs").get())
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(17)
    }
}