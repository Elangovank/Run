plugins {
    alias(libs.plugins.run.android.library)
}

android {
    namespace = "com.elango.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}