package com.cwl.common.exts

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan

/**
 * 将一段文字中指定range的文字改变大小
 * @param range 要改变大小的文字的范围 eg: 0..1   0到1包含0,1
 * @param scale 缩放值，大于1，则比其他文字大；小于1，则比其他文字小；默认是1.5
 */
fun CharSequence.toSizeSpan(range: IntRange, scale: Float = 1.5f): CharSequence {
    return SpannableStringBuilder(this).apply {
        setSpan(RelativeSizeSpan(scale), range.start, range.endInclusive, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}

/**
 * 将指定的str改变大小
 */
fun CharSequence.toSizeSpan(str: CharSequence,scale: Float = 1.5f):CharSequence{
    val start = this.indexOf(str.toString())
    val end=start+str.length-1
    return this.toSizeSpan(start .. end,scale)
}

/**
 * 同时指定多个str改变大小
 */
fun CharSequence.toSizeSpan(vararg strs:CharSequence,scale: Float = 1.5f):CharSequence{
    var origin=SpannableStringBuilder(this)
    strs.forEach {
        origin.append(it.toSizeSpan(it,scale))
    }
    return origin
}

/**
 * 将一段文字中指定range的文字改变前景色
 * @param range 要改变前景色的文字的范围
 * @param color 要改变的颜色，默认是红色
 */
fun CharSequence.toColorSpan(range: IntRange, color: Int = Color.RED): CharSequence {
    return SpannableStringBuilder(this).apply {
        setSpan(ForegroundColorSpan(color), range.start, range.endInclusive, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}

/**
 * 将指定的str改变颜色
 */
fun CharSequence.toColorSpan(str: CharSequence,color: Int = Color.RED):CharSequence{
    val start = this.indexOf(str.toString())
    val end=start+str.length-1
    return this.toColorSpan(start .. end,color)
}

/**
 * 将一段文字中指定range的文字改变背景色
 * @param range 要改变背景色的文字的范围
 * @param color 要改变的颜色，默认是红色
 */
fun CharSequence.toBackgroundColorSpan(range: IntRange, color: Int = Color.RED): CharSequence {
    return SpannableStringBuilder(this).apply {
        setSpan(BackgroundColorSpan(color), range.start, range.endInclusive, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}

/**
 * 将一段文字中指定range的文字添加删除线
 * @param range 要添加删除线的文字的范围
 */
fun CharSequence.toStrikeThrougthSpan(range: IntRange): CharSequence {
    return SpannableStringBuilder(this).apply {
        setSpan(StrikethroughSpan(), range.start, range.endInclusive, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}