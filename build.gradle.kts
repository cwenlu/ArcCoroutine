import org.jetbrains.kotlin.config.KotlinCompilerVersion

// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
//    projectGeneralCls(/*KotlinCompilerVersion.VERSION*/"1.3.41")
    dependencies {
        classpath("com.android.tools.build:gradle:3.4.2")
        classpath(kotlin("gradle-plugin",version= "1.3.41"))
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url="https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


