package com.cwl.common.widget.recyclerview

import com.cwl.common.widget.recyclerview.multitype.MultiTypeAdapter
import java.util.*

/**

 * @Author cwl

 * @Date 2019-09-08 14:19
    扩展了{@link ItemTouchHelper}功能
 */
class ItemTouchAdapter(items: List<Any>): MultiTypeAdapter(items),ItemTouchHelperCallbackAdapter {

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(items,fromPosition,toPosition)
        notifyItemMoved(fromPosition,toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        (items as? MutableList)?.removeAt(position)
        notifyItemRemoved(position)
    }
}