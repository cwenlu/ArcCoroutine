package com.cwl.common.exts

import android.content.Context
import android.content.SharedPreferences

fun sharedPref(name:String="shared_pref_data",mode:Int= Context.MODE_PRIVATE)=ContextProvider.appCtx.getSharedPreferences(name,mode)

fun <V> SharedPreferences.put(key:String,value:V)=edit().apply {
    when(value){
        is Int->putInt(key,value)
        is Float->putFloat(key,value)
        is Long->putLong(key,value)
        is Boolean->putBoolean(key,value)
        is String->putString(key,value)
        is Set<*>->putStringSet(key,value as? Set<String>?:throw IllegalArgumentException("Unsupported types"))
    }
}.apply()

fun SharedPreferences.remove(key:String)=edit().remove(key).apply()

fun SharedPreferences.clear()=edit().clear().apply()

fun Pair<String,*>.putSharePref()=sharedPref().put(first,second)

fun <V> String.putSharePref(value:V)=sharedPref().put(this,value)

fun String.getSharePref()=

fun String.removeSharePref()=sharedPref().remove(this)

fun clearSharePref()=sharedPref().clear()
