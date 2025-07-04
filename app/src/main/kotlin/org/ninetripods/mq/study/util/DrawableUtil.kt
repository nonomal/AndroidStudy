package org.ninetripods.mq.study.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

/**
 * @param context 上下文，用于转换 dp 到 px
 * @param heightDp 分割线的高度（单位：dp）
 * @param color 分割线的颜色
 * @param drawable 传入的drawable
 * @param orientation 布局方向[DividerItemDecoration.VERTICAL]、[DividerItemDecoration.HORIZONTAL]
 */
fun RecyclerView.createDivider(
    context: Context,
    dividerDp: Float = 0.5f,
    color: Int = Color.TRANSPARENT,
    drawable: Drawable? = null,
    orientation: Int = DividerItemDecoration.VERTICAL,
) {
    //优先使用传入的drawable，没有传入的话创建动态分割线Drawable
    val shapeDrawable = drawable ?: ShapeDrawable(RectShape()).apply {
        // 设置分割线的高度
        val dividerPx = (dividerDp * context.resources.displayMetrics.density + 0.5f).toInt()
        if (orientation == DividerItemDecoration.HORIZONTAL) {
            intrinsicWidth = dividerPx
        } else {
            intrinsicHeight = dividerPx
        }
        // 设置分割线的颜色
        paint.color = color
    }
    //将动态分割线添加到 RecyclerView
    val dividerItemDecoration = DividerItemDecoration(context, orientation).apply {
        setDrawable(shapeDrawable)
    }
    this.addItemDecoration(dividerItemDecoration)
}

