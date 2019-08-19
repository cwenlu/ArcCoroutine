package com.cwl.common.exts

import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期对应的毫秒值
 */
fun String.toDateMills(format: String = "yyyy-MM-dd HH:mm:ss") = SimpleDateFormat(format, Locale.getDefault()).parse(this).time

/**
 * 时间戳转日期格式
 */
fun Long.toDateString(format: String = "yyyy-MM-dd HH:mm:ss") = SimpleDateFormat(format, Locale.getDefault()).format(Date(this))

//fun String.dateFormat(infmt:String="yyyy-MM-dd HH:mm:ss",outfmt:String)=