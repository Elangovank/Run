import com.android.build.api.dsl.ApplicationExtension
import com.elango.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("run.android.application")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<ApplicationExtension> {
                configureCompose(this)
            }
        }
    }
}