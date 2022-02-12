package info.hellovass.bugly

import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.api.BaseVariant
import org.gradle.api.GradleException
import org.gradle.api.Project

interface ProjectContext {

    val target: Project

    val allVariants: Set<BaseVariant>
}

internal class ProjectContextImpl(override val target: Project) : ProjectContext {

    override val allVariants: Set<BaseVariant>
        get() = target._allVariants

    private val Project._allVariants: Set<BaseVariant>
        get() = when {
            _isAndroidPlugin ->
                (_androidExtension as AppExtension).applicationVariants.toSet()
            _isLibraryPlugin ->
                (_androidExtension as LibraryExtension).libraryVariants.toSet()
            else ->
                throw GradleException("don't support")
        }

    private val Project._androidExtension: BaseExtension
        get() {
            return extensions.getByName("android") as BaseExtension
        }

    private val Project._isAndroidPlugin: Boolean
        get() {
            return plugins.hasPlugin("com.android.application")
        }

    private val Project._isLibraryPlugin: Boolean
        get() {
            return plugins.hasPlugin("com.android.library")
        }
}
