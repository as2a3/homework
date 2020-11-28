package com.said.homework.news.presentation.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.said.homework.R
import com.said.homework.base.presentation.view.holder.RecyclerLoadMoreViewHolder
import com.said.homework.news.presentation.model.ArticleUI
import com.said.homework.news.presentation.view.holder.NewsAdapterViewHolder
import java.util.*
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 25/11/2020.
 */
class NewsAdapter @Inject constructor(private val mContext: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var loadMore = false
    private var articleUIS: ArrayList<ArticleUI>? = ArrayList()
    private var mCallback: Callback? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            ITEM -> {
                val viewItem = inflater.inflate(R.layout.item_news, parent, false)
                viewHolder = NewsAdapterViewHolder(viewItem)
            }
            LOADING -> {
                val viewLoading =
                    inflater.inflate(R.layout.recycler_progress_view_holder, parent, false)
                viewHolder = RecyclerLoadMoreViewHolder(viewLoading, loadMore)
            }
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> {
                val itemHolder = holder as NewsAdapterViewHolder
                itemHolder.setViewsData(mContext, articleUIS!![position], mCallback!!)
            }
            LOADING -> {
                val loadingHolder = holder as RecyclerLoadMoreViewHolder
                loadingHolder.isLoadMore(loadMore)
            }
        }
    }

    private val basicItemCount: Int
        get() = if (articleUIS == null) 0 else articleUIS!!.size

    override fun getItemCount(): Int {
        return basicItemCount + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) LOADING else ITEM
    }

    fun setIsLoadMore(state: Boolean) {
        loadMore = state
    }

    fun setArticleUIS(articleUIS: ArrayList<ArticleUI>?) {
        this.articleUIS = articleUIS
    }

    fun setListener(callback: Callback?) {
        mCallback = callback
    }

    interface Callback {
        fun onArticleItemSelected(articleUI: ArticleUI?)
    }

    companion object {
        // View Types
        private const val ITEM = 0
        private const val LOADING = 1
    }
}