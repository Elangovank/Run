import org.gradle.kotlin.dsl.compileOnly

plugins {
    `kotlin-dsl`
}
group = "com.elango.run.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins.register("androidApplication") {
        id = "run.android.application"
        implementationClass = "AndroidApplicationConventionPlugin"
    }
}