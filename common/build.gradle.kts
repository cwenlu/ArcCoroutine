import org.jetbrains.kotlin.config.KotlinCompilerVersion
plugins{
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.0")


    defaultConfig {
        minSdkVersion(15)
        targetSdkVersion(29)
        versionCode=1
        versionName="1.0"

        testInstrumentationRunner="androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

    }

}

projectGeneralDeps(KotlinCompilerVersion.VERSION)

api(
    deps.kotlinx_coroutines_core,
    deps.moshi_kotlin,
    deps.PersistentCookieJar,
    deps.timber,
    deps.okhttp3_logging_interceptor,
    deps.lifecycle_extensions,
    deps.lifecycle_viewmodel_ktx,
    deps.lifecycle_common_java8,
    deps.appcompat,
    deps.koin_core

)

cmpOnly(
    deps.picasso
)

