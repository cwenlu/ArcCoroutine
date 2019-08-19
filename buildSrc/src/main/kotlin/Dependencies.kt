import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

/**
 * 项目通用的一些依赖配置
 */
fun Project.projectGeneralDeps(kotlinVersion: String) {
    dependencies {
        "testImplementation"("junit:junit:4.12")
        "androidTestImplementation"("androidx.test:runner:1.2.0")
        "androidTestImplementation"("androidx.test.espresso:espresso-core:3.2.0")
        "implementation"(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        "implementation"(kotlin("stdlib-jdk7", kotlinVersion))
    }
}

/**
 * 统一的一些classpath 配置
 */
fun Project.projectGeneralCls(kotlinVersion: String){
    dependencies{
        clspath(
            deps.gradlde,
            kotlin("gradle-plugin",version= kotlinVersion)
        )
    }
}

/**
 * 注册多个插件classpath
 */
fun Project.clspath(vararg deps:Any){
    dependencies{
        deps.forEach {
            "classpath"(it)
        }
    }
}

/**
 * 注册多个implementation方式的依赖
 */
fun Project.impl(vararg deps:String){
    dependencies{
        deps.forEach {
            "implementation"(it)
        }
    }

}

/**
 * 注册多个api方式的依赖
 */
fun Project.api(vararg deps:String){
    dependencies{
        deps.forEach {
            "api"(it)
        }
    }
}

/**
 * 注册多个编译时注解器
 */
fun Project.kapt(vararg deps:String){
    dependencies{
        deps.forEach {
            "kapt"(it)
        }
    }
}

/**
 * 注册多个只编译时用的依赖
 */
fun Project.cmpOnly(vararg deps:String){
    dependencies{
        deps.forEach {
            "compileOnly"(it)
        }
    }
}

object deps{
    private val lifecycle_version="2.2.0-alpha02"
    /**
     * v7
     */
    val appcompat="androidx.appcompat:appcompat:1.0.2"
    val lifecycle_extensions="androidx.lifecycle:lifecycle-extensions:$lifecycle_version"
    val lifecycle_viewmodel_ktx="androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    val lifecycle_common_java8="androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
    val okhttp3_logging_interceptor="com.squareup.okhttp3:logging-interceptor:3.14.1"
    /**
     * json处理
     */
    val moshi_kotlin="com.squareup.moshi:moshi-kotlin:1.8.0"
    /**
     * kapt moshi用注解处理器时配置
     */
    val moshi_kotlin_codegen="com.squareup.moshi:moshi-kotlin-codegen:1.8.0"
    /**
     * okhttp cookie 持久化
     */
    val PersistentCookieJar="com.github.franmontiel:PersistentCookieJar:v1.0.1"
    /**
     * 日志
     */
    val timber="com.jakewharton.timber:timber:4.7.1"

    val picasso="com.squareup.picasso:picasso:2.71828"

    val kotlinx_coroutines_core="org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.0-RC"

    val koin_core="org.koin:koin-core:2.0.1"
    /**
     * classpath 可打印方法耗时，入参，返回值
     */
    val hugo="com.jakewharton.hugo:hugo-plugin:1.2.1"


    //-----------classpath---------------
    val gradlde="com.android.tools.build:gradle:3.4.2"
}