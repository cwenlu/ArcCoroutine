package com.cwl.common.imageloader

import android.widget.ImageView
import java.io.File

var globalImageLoader:IImageLoader<ImageLoaderOptions>?=null
fun <IL : IImageLoader<ImageLoaderOptions>> IL.register(){
    globalImageLoader=this
}

/**
 * 可以不调用
 */
fun <IL : IImageLoader<ImageLoaderOptions>> IL.unRegister(){
    globalImageLoader=null
}

private inline fun <ILO : ImageLoaderOptions> load(noinline block:(ILO.()->Unit)?):IImageLoader<ILO> {
    var iImageLoader = globalImageLoader as? IImageLoader<ILO>?:throw NullPointerException("globalImageLoader is null")
    iImageLoader?.options().apply{block?.invoke(this)}
    return iImageLoader
}


fun <ILO : ImageLoaderOptions> ImageView.load(path:String,block:(ILO.()->Unit)?=null)=load(block).load(this,path)

fun <ILO : ImageLoaderOptions> ImageView.load(resId:Int,block:(ILO.()->Unit)?)=load(block).load(this,resId)

fun <ILO : ImageLoaderOptions> ImageView.load(file:File,block:(ILO.()->Unit)?)=load(block).load(this,file)

