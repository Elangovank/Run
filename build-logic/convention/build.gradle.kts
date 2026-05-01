plugins {
    `kotlin-dsl`
}
group = "com.elango.run.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.android.tools.common)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.kotlin.serialization.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins.register("androidApplication") {
        id = "run.android.application"
        implementationClass = "AndroidApplicationConventionPlugin"
    }

    plugins.register("androidApplicationCompose") {
        id = "run.android.application.compose"
        implementationClass = "AndroidApplicationComposeConventionPlugin"
    }

    plugins.register("androidLibrary") {
        id = "run.android.library"
        implementationClass = "AndroidLibraryConventionPlugin"
    }
    plugins.register("androidLibraryCompose") {
        id = "run.android.library.compose"
        implementationClass = "AndroidLibraryComposePlugin"
    }
    plugins.register("androidFeatureUiLibrary") {
        id = "run.android.library.feature.ui.compose"
        implementationClass = "AndroidFeatureUiConventionPlugin"
    }
    plugins.register("androidRoomLibrary") {
        id = "run.android.room"
        implementationClass = "AndroidRoomConventionPlugin"
    }
    plugins.register("JvmLibrary") {
        id = "run.jvm.library"
        implementationClass = "JvmLibraryConventionPlugin"
    }
    plugins.register("JvmKtorLibrary") {
        id = "run.jvm.ktor.library"
        implementationClass = "JvmKtorConventionPlugin"
    }


}