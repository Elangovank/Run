import com.elango.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.jvm")
            }
            configureKotlinJvm()
        }
    }
}