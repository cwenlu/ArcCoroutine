package com.cwl.okhttpdsl.http.util

import java.util.*

object BeanUtil {
    fun bean2Map(bean: Any): Map<String, Any> {
        val map = HashMap<String,Any>()
        val clazz = bean.javaClass
        val declaredFields = clazz.declaredFields
        for (declaredField in declaredFields) {
            declaredField.isAccessible = true
            try {
                val value = declaredField.get(bean)
                if (value != null) {
                    map.put(declaredField.name, value)
                }

            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
        //屏蔽冗余
        //https://segmentfault.com/q/1010000008078789
        map.remove("\$change")
        map.remove("serialVersionUID")
        return map
    }

}