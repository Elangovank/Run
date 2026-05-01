import com.android.build.api.dsl.LibraryExtension
import com.elango.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposePlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("run.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<LibraryExtension>{
                configureCompose(this)
            }
        }
    }
}