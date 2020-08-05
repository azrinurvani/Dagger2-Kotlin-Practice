package com.mobile.azrinurvani.dagger2kotlinpractice.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


//TODO 78 - Create VerticalSpaceItemDecoration.class
class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {
    fun getItemOffsets(
        view: View?, outRect: Rect, parent: RecyclerView?,
        state: RecyclerView.State?
    ) {
        outRect.bottom = verticalSpaceHeight
    }

}