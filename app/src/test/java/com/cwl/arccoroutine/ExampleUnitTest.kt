package com.cwl.arccoroutine

import com.cwl.common.imageloader.ImageLoaderOptions
import com.cwl.common.imageloader.PicassoImageLoader
import org.junit.Test

import org.junit.Assert.*
import java.lang.reflect.ParameterizedType

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        B<ZZ>().cc()
    }


}

class ZZ

interface A<a:ZZ>{
    fun cc()
}

open class B<a:ZZ> :A<ZZ>{
    override fun cc(){
        for (actualTypeArgument in (javaClass.genericInterfaces[0] as ParameterizedType).actualTypeArguments) {
            println(actualTypeArgument)
        }
    }
}

class C :B<ZZ>(){
    override fun cc(){
//        for (actualTypeArgument in (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments) {
//            println(actualTypeArgument)
//        }

        for (actualTypeArgument in (javaClass.superclass!!.genericInterfaces[0] as ParameterizedType).actualTypeArguments) {
            println(actualTypeArgument)
        }
    }
}