package com.cwl.common.widget.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**

 * @Author cwl

 * @Date 2019-08-26 15:14

 */
class CommonViewHolder (itemView: View):RecyclerView.ViewHolder(itemView),LayoutContainer {
    override val containerView: View?
        get() = itemView

    constructor(parent:ViewGroup,@LayoutRes layoutId:Int) : this(LayoutInflater.from(parent.context).inflate(layoutId,parent,false))


}