package com.said.homework.news.presentation.view.holder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.said.homework.base.presentation.util.ImageLoader.loadImage
import com.said.homework.databinding.ItemNewsBinding
import com.said.homework.news.presentation.model.ArticleUI
import com.said.homework.news.presentation.view.adapter.NewsAdapter

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
class NewsAdapterViewHolder(v: View?) : RecyclerView.ViewHolder(v!!) {
    private val viewBinding: ItemNewsBinding = ItemNewsBinding.bind(v!!)
    fun setViewsData(
        mContext: Context?, articleUI: ArticleUI,
        mCallback: NewsAdapter.Callback
    ) {
        loadImage(mContext, articleUI.urlToImage, viewBinding.articleImageView)
        viewBinding.titleTextView.text = articleUI.title
        viewBinding.descriptionTextView.text = articleUI.description
        viewBinding.root.setOnClickListener { v: View? -> mCallback.onArticleItemSelected(articleUI) }
    }
}