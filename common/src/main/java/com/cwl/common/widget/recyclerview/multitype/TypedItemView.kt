package com.cwl.common.widget.recyclerview

import androidx.annotation.LayoutRes

/**

 * @Author cwl

 * @Date 2019-08-25 19:16

 */
abstract class TypedItemView<T>(@LayoutRes val layoutId:Int) {

    abstract fun isForViewType(item:T,position:Int):Boolean

    abstract fun onBindViewHolder(holder: CommonViewHolder, t:T, position: Int)

}