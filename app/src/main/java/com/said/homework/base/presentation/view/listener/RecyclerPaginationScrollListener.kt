package com.said.homework.base.presentation.view.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
abstract class RecyclerPaginationScrollListener : RecyclerView.OnScrollListener() {
    private var mScrolledDistance = 0
    private var mControlsVisible = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val firstVisibleItem = (Objects.requireNonNull(
            recyclerView.layoutManager
        ) as LinearLayoutManager).findFirstVisibleItemPosition()
        if (firstVisibleItem == 0) {
            if (!mControlsVisible) {
                onScrollToTop()
                mControlsVisible = true
            }
        } else {
            if (mScrolledDistance > HIDE_THRESHOLD && mControlsVisible) {
                onScrollToDown()
                mControlsVisible = false
                mScrolledDistance = 0
            } else if (mScrolledDistance < -HIDE_THRESHOLD && !mControlsVisible) {
                onScrollToTop()
                mControlsVisible = true
                mScrolledDistance = 0
            }
        }
        if (mControlsVisible && dy > 0 || !mControlsVisible && dy < 0) {
            mScrolledDistance += dy
        }
        if (dy > 0) //check for scroll down
        {
            val visibleItemCount = recyclerView.layoutManager!!.childCount
            val totalItemCount = recyclerView.layoutManager!!.itemCount
            val pastVisibleItems =
                (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                onLoadMore()
            }
        }
    }

    abstract fun onScrollToDown()
    abstract fun onScrollToTop()

    //    public abstract void swipeRefresh(boolean state);
    abstract fun onLoadMore()

    companion object {
        private const val HIDE_THRESHOLD = 20
    }
}