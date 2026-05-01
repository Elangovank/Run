package com.elango.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies


fun DependencyHandlerScope.addFeatureUiDependencies(project: Project) {
    project.run {
        dependencies {
            "implementation"(project(":core:presentation:ui"))
            "implementation"(project(":core:presentation:designsystem"))

            "implementation"(project.libs.findBundle("koin.compose").get())
            "implementation"(project.libs.findBundle("compose").get())
            "debugImplementation"(project.libs.findBundle("compose.debug").get())
            "androidTestImplementation"(project.libs.findLibrary("androidx.compose.ui.test.junit4").get())
        }
    }

}