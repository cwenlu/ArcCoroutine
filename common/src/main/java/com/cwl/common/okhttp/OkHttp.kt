package com.cwl.common.okhttp

import android.content.Context
import com.cwl.common.di.koin
import com.cwl.okhttpdsl.http.config.OkHttpConfig
import com.cwl.okhttpdsl.http.config.RequestBuilder
import com.cwl.okhttpdsl.http.util.BeanUtil

fun String.http()= RequestBuilder(this)

fun RequestParams.http()=RequestBuilder(url).params(BeanUtil.bean2Map(this))

/**
 * bean作为请求载体时继承
 */
open class RequestParams(open var url:String="")

/**
 * 全局配置
 */
fun OkHttpConfig(context: Context, block: OkHttpConfig.() -> Unit) = koin.get<OkHttpConfig>().apply{
    this.context=context
    block()
}