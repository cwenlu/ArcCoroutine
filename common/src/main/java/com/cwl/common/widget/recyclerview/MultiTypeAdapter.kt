package com.cwl.common.widget.recyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**

 * @Author cwl

 * @Date 2019-08-25 19:44

 */
open class MultiTypeAdapter(var items: List<Any> = emptyList()) :RecyclerView.Adapter<CommonViewHolder>() {
    private val typedItemViewManager=TypedItemViewManager<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        return CommonViewHolder(parent,typedItemViewManager.get(viewType)?.layoutId!!)
    }

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        typedItemViewManager.onBindViewHolder(holder,items.get(position),position)
    }

    override fun getItemViewType(position: Int): Int {
        return typedItemViewManager.getItemViewType(items[position],position)
    }

    fun <T> register(typedItemView:TypedItemView<T>){
        typedItemViewManager.add(typedItemView as TypedItemView<Any>)
    }


    /**
     * 单一样式
     */
    fun <T> register(@LayoutRes layoutId:Int,onBindViewHolder:(CommonViewHolder,T,Int)->Unit){
        typedItemViewManager.add(object : TypedItemView<T>(layoutId) {

            override fun isForViewType(item: T, position: Int): Boolean =true

            override fun onBindViewHolder(holder: CommonViewHolder, t: T, position: Int) =onBindViewHolder(holder,t,position)

        } as TypedItemView<Any>)
    }

}