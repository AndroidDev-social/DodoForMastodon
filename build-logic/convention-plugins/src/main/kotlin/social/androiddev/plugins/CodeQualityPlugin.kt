package social.androiddev.plugins

import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.File

class CodeQualityPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply(SpotlessPlugin::class.java)
        extensions.configure<SpotlessExtension> {
            kotlin {
                target("src/*/kotlin/**/*.kt")
                ktlint("0.43.2")
                licenseHeaderFile(File(rootDir, "copyright.txt"))
            }
        }
    }
}