plugins {
    alias(libs.plugins.run.android.library.feature.ui.compose)
}

android {
    namespace = "com.elango.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}