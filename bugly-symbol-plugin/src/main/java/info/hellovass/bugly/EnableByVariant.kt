package info.hellovass.bugly

import com.android.build.gradle.api.BaseVariant
import groovy.lang.Closure

/**
 *
 */
typealias EnableByVariant = (variant: BaseVariant) -> Boolean

/**
 * author: hellovass
 * time  : 2021/12/22 7:43 下午
 * desc  :
 */
abstract class EnableByFeatureExtension {

    private var kotlinEnableByVariant: EnableByVariant? = null

    private var groovyEnableByVariant: Closure<Boolean>? = null

    fun enableByVariant(selector: EnableByVariant) {
        kotlinEnableByVariant = selector
    }

    fun enableByVariant(selector: Closure<Boolean>) {
        groovyEnableByVariant = selector.dehydrate()
    }

    fun isFeatureEnabled(variant: BaseVariant): Boolean {
        return when {
            kotlinEnableByVariant != null ->
                kotlinEnableByVariant?.invoke(variant)
            groovyEnableByVariant != null ->
                groovyEnableByVariant?.call(variant)
            else ->
                false
        } ?: false
    }
}
