package info.hellovass.bugly

/**
 * author: hellovass
 * time  : 2022/2/9 6:32 下午
 * desc  :
 */
abstract class BuglySymbolExtension : EnableByFeatureExtension() {

    /**
     * jdk8 路径
     */
    var jdk8Path: String? = null

    /**
     * bugly 工具路径
     */
    var buglyToolsPath: String? = null

    /**
     *
     */
    var appConfig: AppConfig = AppConfig()
}