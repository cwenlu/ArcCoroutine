package com.cwl.common.exts

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cwl.common.util.UniversalItemDecoration
import kotlin.math.abs



/**

 * @Author cwl

 * @Date 2019-08-30 20:01

 */


interface OnItemClickListener {
    fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int)
    fun onItemLongClick(viewHolder: RecyclerView.ViewHolder, position: Int)
}

fun RecyclerView.onItemClick(onItemClick: (RecyclerView.ViewHolder, Int) -> Unit) {
    addItemClickListener(object : OnItemClickListener {

        override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int) {
            onItemClick.invoke(viewHolder, position)
        }

        override fun onItemLongClick(viewHolder: RecyclerView.ViewHolder, position: Int) {

        }
    })
}

fun RecyclerView.onItemLongClick(onItemLongClick: (RecyclerView.ViewHolder, Int) -> Unit) {
    addItemClickListener(object : OnItemClickListener {

        override fun onItemClick(viewHolder: RecyclerView.ViewHolder, position: Int) {
        }

        override fun onItemLongClick(viewHolder: RecyclerView.ViewHolder, position: Int) {
            onItemLongClick(viewHolder, position)
        }
    })
}

private fun RecyclerView.addItemClickListener(listener: OnItemClickListener) {

    val gestureDetector = GestureDetectorCompat(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            val childView = findChildViewUnder(e.x, e.y)
            if (childView != null) {
                listener.onItemClick(getChildViewHolder(childView), getChildAdapterPosition(childView))
            }
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            super.onLongPress(e)
            val childView = findChildViewUnder(e.x, e.y)
            if (childView != null) {
                listener.onItemLongClick(getChildViewHolder(childView), getChildAdapterPosition(childView))
            }
        }
    })

    this.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            gestureDetector.onTouchEvent(e)
            return false
        }

    })
}

/**
 * 设置layoutmanager
 */
fun RecyclerView.setupLayoutManager(
    spanCount: Int = 0,
    isStaggered: Boolean = false, @RecyclerView.Orientation orientation: Int = RecyclerView.VERTICAL
): RecyclerView {
    layoutManager = LinearLayoutManager(context, orientation, false)
    if (isStaggered) {
        layoutManager = StaggeredGridLayoutManager(spanCount, orientation)
    } else if (spanCount != 0) {
        layoutManager = GridLayoutManager(context, spanCount, orientation, false)
    }

    return this
}

/**
 * @inclusiveStart 是否绘制开始的分割线
 * @inclusiveEnd 是否绘制结束的分割线
 */
fun RecyclerView.divider(
    @ColorInt color: Int, @Px gap: Int, inclusiveStart: Boolean = false,
    inclusiveEnd: Boolean = false
) {
    addItemDecoration(object : UniversalItemDecoration() {
        override fun getItemOffsets(position: Int,spanIndex:Int?): Decoration =
            ColorDecoration().apply {
                decorationColor = color
                if (inclusiveStart && position == 0) top = gap
                bottom = gap
                if (!inclusiveEnd && position == adapter?.itemCount ?: 0 - 1) bottom = 0
            }
    })
}

/**
 * 间距全等
 */
fun RecyclerView.gridDivider(@Px gap:Int,@ColorInt color: Int?=null)=gridDivider(color,gap,gap,gap,gap,gap)

/**
 * @degeGap 4周的距离
 */
fun RecyclerView.gridDivider(@Px gap:Int,@ColorInt color: Int?=null,@Px degeGap:Int)=gridDivider(color,gap,gap,degeGap,degeGap,degeGap)

/**
 * @bottom 对瀑布流不起作用
 */
fun RecyclerView.gridDivider(@ColorInt color: Int?=null, @Px rowGap: Int, @Px columnGap: Int, @Px leftAndRight: Int, @Px top: Int, @Px bottom: Int?=null) {
    var spanCount=0
    var orientation=RecyclerView.VERTICAL
    var staggered=false
    (layoutManager as? GridLayoutManager)?.apply {
        spanCount=this.spanCount
        orientation=this.orientation
    }?:(layoutManager as? StaggeredGridLayoutManager)?.apply {
        spanCount=this.spanCount
        orientation=this.orientation
        staggered=true
    }?:throw TypeCastException("LayoutManager isn't GridLayoutManager or LayoutManager==null")


    addItemDecoration(object : UniversalItemDecoration() {
        override fun getItemOffsets(position: Int,spanIndex:Int?): Decoration =
            ColorDecoration().apply {
                if(color!=null) decorationColor = color
                var pos=spanIndex?:position
                if(orientation==RecyclerView.VERTICAL){

                    if (pos % spanCount == 0){//first column
                        left=leftAndRight
                        right=columnGap/2

                    }else if((pos+1)%spanCount==0){//last column
                        left=columnGap/2
                        right=leftAndRight

                    }else{
                        left=columnGap/2
                        right=columnGap/2
                    }

                    if(!staggered){
                        //计算行数
                        val lines = if (adapter!!.itemCount % spanCount == 0) adapter!!.itemCount / spanCount else adapter!!.itemCount / spanCount + 1

                        if(position<spanCount){//first row
                            this.top=top
                            this.bottom=rowGap/2
                        }else if(position/spanCount+1==lines){//last row
                            this.top=rowGap/2
                            if(bottom!=null){
                                this.bottom=bottom
                            }
                        }else{
                            this.top=rowGap/2
                            this.bottom=rowGap/2
                        }
                    }else{
                        if(position<spanCount) {//first row
                            this.top=top
                        }
                        this.bottom=rowGap
                    }

                }else{

                    //todo
                }


            }
    })
}





/**
 * 滚动到底部回调
 */
fun RecyclerView.atTheBottom(block: (Boolean) -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            block(!canScrollVertically(1))
        }
    })
}