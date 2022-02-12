package info.hellovass.bugly

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

open class UploadSymbolTask : DefaultTask() {

    @get:Input
    lateinit var jdk8Path: String

    @get:Input
    lateinit var buglyToolsPath: String

    @get:Input
    lateinit var appConfig: AppConfig

    @get:Internal
    lateinit var mappingFileProvider: Provider<FileCollection>

    @TaskAction
    fun run() {

        val mappingFile = mappingFileProvider.get().singleFile

        val cmd = "$jdk8Path " +
                "-jar $buglyToolsPath " +
                "-appid ${appConfig.appId} " +
                "-appkey ${appConfig.appKey} " +
                "-bundleid ${appConfig.bundleId} " +
                "-version ${appConfig.versionName} " +
                "-platform Android " +
                "-inputMapping " + mappingFile

        println(cmd.execute())
    }
}
