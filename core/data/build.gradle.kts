plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.run.jvm.ktor.library)
}

android {
    namespace = "com.elango.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)
    implementation(libs.datastore)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}