plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.run.jvm.ktor.library)
}

android {
    namespace = "com.elango.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}