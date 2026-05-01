plugins {
    alias(libs.plugins.run.android.library)
    alias(libs.plugins.run.jvm.ktor.library)
}

android{
    namespace = "com.elango.auth.data"
}
dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}