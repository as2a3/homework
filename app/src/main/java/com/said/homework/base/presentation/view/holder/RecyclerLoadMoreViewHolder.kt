package com.said.homework.base.presentation.view.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.said.homework.databinding.RecyclerProgressViewHolderBinding

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
class RecyclerLoadMoreViewHolder(itemView: View?, loadMore: Boolean) : RecyclerView.ViewHolder(
    itemView!!
) {
    private val binding: RecyclerProgressViewHolderBinding = RecyclerProgressViewHolderBinding.bind(itemView!!)
    fun isLoadMore(loadMore: Boolean) {
        if (loadMore) {
            binding.loadMoreProgress.visibility = View.VISIBLE
        } else {
            binding.loadMoreProgress.visibility = View.GONE
        }
    }

    init {
        if (!loadMore) {
            binding.loadMoreProgress.visibility = View.GONE
        }
    }
}