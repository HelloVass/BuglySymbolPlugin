package info.hellovass.bugly

import org.gradle.api.Project

fun Project.withContext(action: ProjectContext.() -> Unit) {
    afterEvaluate {
        val projectContext = ProjectContextImpl(target = this)
        projectContext.action()
    }
}