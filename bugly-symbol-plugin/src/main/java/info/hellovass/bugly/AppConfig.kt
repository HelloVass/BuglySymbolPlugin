package info.hellovass.bugly

import org.gradle.api.tasks.Input

data class AppConfig(
    /**
     *
     */
    @get:Input
    var appId: String? = null,

    /**
     *
     */
    @get:Input
    var appKey: String? = null,

    /**
     *
     */
    @get:Input
    var versionName: String? = null,

    /**
     *
     */
    @get:Input
    var bundleId: String? = null
)