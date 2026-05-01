plugins {
    alias(libs.plugins.run.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}
