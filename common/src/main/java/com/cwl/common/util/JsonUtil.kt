package com.cwl.okhttpdsl.http.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtil {
    var moshi:Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    inline fun <reified T> fromJson(json:String):T?=generateAdapter<T>().fromJson(json)

    inline fun <reified T> toJson(t:T)=generateAdapter<T>().toJson(t)


    inline fun <reified T> fromJson2List(json: String):List<T>?=generateListAdapter<T>().fromJson(json)

    inline fun <reified K,reified V> fromJson2Map(json: String):Map<K,V>?=generateMapAdapter<K,V>().fromJson(json)

    /**
     * 构造一般对象adapter
     */
    inline fun <reified T> generateAdapter()=moshi.adapter(T::class.java)

    /**
     * 构造list adapter
     */
    inline fun <reified T> generateListAdapter(): JsonAdapter<List<T>> {
        var newParameterizedType = Types.newParameterizedType(List::class.java, T::class.java)
        return moshi.adapter(newParameterizedType)
    }

    /**
     * 构造map adapter
     */
    inline fun <reified K,reified V> generateMapAdapter():JsonAdapter<Map<K,V>>{
        var newParameterizedType = Types.newParameterizedType(Map::class.java, K::class.java, V::class.java)
        return moshi.adapter(newParameterizedType)
    }


}
