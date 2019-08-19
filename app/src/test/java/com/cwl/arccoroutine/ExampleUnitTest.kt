package com.cwl.arccoroutine

import org.junit.Test
import java.lang.reflect.ParameterizedType

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        B<ZZ>().cc()
//        kk("s","ss",Any())
        println("123".indexOf("23"))
    }


}

fun kk(vararg m:Any){
    m.forEach {
        println(it)
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