package info.hellovass.bugly

import org.codehaus.groovy.runtime.ProcessGroovyMethods
import java.io.File

fun String.execute(
    envp: Array<String>? = null,
    dir: File? = null,
    interruptWhenCmdFailed: Boolean = false
): String {

    val process = ProcessGroovyMethods.execute(
        this,
        envp,
        dir
    )

    return when (process.waitFor()) {
        0 ->
            process.inputStream.bufferedReader().use { reader ->
                return@use reader.readText()
            }
        else ->
            process.inputStream.bufferedReader().use { reader ->

                val errorMsg = "[CMD] - fail to execute command [${this}]" +
                        "under ${dir}\n " +
                        "message: ${reader.readText()}"

                if (interruptWhenCmdFailed)
                    throw RuntimeException(errorMsg)

                return@use errorMsg
            }
    }
}