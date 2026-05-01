plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.run.jvm.ktor.library)
}

android {
    namespace = "com.elango.run.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)
    implementation(libs.androidx.work)
    implementation(libs.koin.android.workmanager)
    implementation(libs.kotlinx.serialization.json)

    implementation(projects.run.domain)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}