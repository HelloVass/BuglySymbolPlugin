package info.hellovass.bugly

import com.android.build.gradle.api.BaseVariant
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.create

class BuglySymbolPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val extension = project.extensions.create<BuglySymbolExtension>("BuglySymbol")

        project.withContext {

            allVariants.forEach { variant: BaseVariant ->

                if (!extension.isFeatureEnabled(variant)) {
                    return@forEach
                }

                val uploadSymbolTask = project.tasks.register(
                    "${variant.name.capitalize()}UploadSymbol",
                    UploadSymbolTask::class.java
                ) {
                    this.group = "report"
                    this.mappingFileProvider = variant.mappingFileProvider
                    this.jdk8Path = checkNotNull(extension.jdk8Path)
                    this.buglyToolsPath = checkNotNull(extension.buglyToolsPath)
                    this.appConfig = extension.appConfig.copy(
                        versionName = (variant.outputs.single() as ApkVariantOutputImpl).versionNameOverride
                    )
                }

                project._hookPackageTask(variant) {
                    finalizedBy(uploadSymbolTask)
                }
            }
        }
    }

    private fun Project._hookPackageTask(
        variant: BaseVariant,
        action: Task.() -> Unit
    ) {
        val targetTask = tasks.named("package${variant.name.capitalize()}")
        targetTask.configure(action)
    }
}