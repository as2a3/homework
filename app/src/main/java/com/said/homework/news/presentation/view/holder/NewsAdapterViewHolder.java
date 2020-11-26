package com.said.homework.news.presentation.view.holder;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.said.homework.base.presentation.util.ImageLoader;
import com.said.homework.databinding.ItemNewsBinding;
import com.said.homework.news.presentation.model.ArticleUI;
import com.said.homework.news.presentation.view.adapter.NewsAdapter;

/**
 * Created by Ahmed Sa'eed on 25,November,2020
 */
public class NewsAdapterViewHolder extends RecyclerView.ViewHolder {
    private final ItemNewsBinding viewBinding;

    public NewsAdapterViewHolder(View v) {
        super(v);
        viewBinding = ItemNewsBinding.bind(v);
    }

    public void setViewsData(Context mContext, ArticleUI articleUI,
                             NewsAdapter.Callback mCallback) {
        ImageLoader.INSTANCE.loadImage(mContext, articleUI.getUrlToImage(), viewBinding.articleImageView);
        viewBinding.titleTextView.setText(articleUI.getTitle());
        viewBinding.descriptionTextView.setText(articleUI.getDescription());
        viewBinding.getRoot().setOnClickListener(v -> mCallback.onArticleItemSelected(articleUI));
    }
}
