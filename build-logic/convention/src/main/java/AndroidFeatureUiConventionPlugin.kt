import com.android.build.api.dsl.LibraryExtension
import com.elango.convention.addFeatureUiDependencies
import com.elango.convention.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("run.android.library.compose")
            }

            dependencies {
                addFeatureUiDependencies(target)
            }
        }
    }
}