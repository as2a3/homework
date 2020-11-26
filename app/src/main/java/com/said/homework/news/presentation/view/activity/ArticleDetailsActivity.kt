package com.said.homework.news.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.viewbinding.ViewBinding
import com.said.homework.R
import com.said.homework.base.presentation.di.HasComponent
import com.said.homework.base.presentation.util.ImageLoader
import com.said.homework.base.presentation.view.activity.BaseMvpActivity
import com.said.homework.databinding.ActivityArticleDetailsBinding
import com.said.homework.news.presentation.contract.ArticleDetailsActivityContract
import com.said.homework.news.presentation.di.component.ArticleDetailsActivityComponent
import com.said.homework.news.presentation.di.module.ArticleDetailsActivityModule
import com.said.homework.news.presentation.model.ArticleUI
import com.said.homework.news.presentation.presenter.ArticleDetailsActivityPresenter
import java.util.*
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
class ArticleDetailsActivity : BaseMvpActivity<ArticleDetailsActivityContract.Presenter?>(),
    ArticleDetailsActivityContract.View, HasComponent<ArticleDetailsActivityComponent?> {

    @Inject
    lateinit var articleDetailsActivityPresenter: ArticleDetailsActivityPresenter
    var articleDetailsActivityComponent: ArticleDetailsActivityComponent? = null
    private var articleUI: ArticleUI? = null

    lateinit var activityArticleDetailsBinding: ActivityArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityArticleDetailsBinding = baseActivityViewBinding as ActivityArticleDetailsBinding
        if (Objects.requireNonNull(intent.extras)!!.containsKey(EXTRA_ARTICLE)) {
            articleUI = intent.getSerializableExtra(EXTRA_ARTICLE) as ArticleUI?
        }
        initializeViews()
    }

    private fun initializeViews() {
        ImageLoader.loadImage(this, articleUI!!.urlToImage, activityArticleDetailsBinding.articleImageView)
        activityArticleDetailsBinding.titleTextView.text = articleUI?.title
        activityArticleDetailsBinding.authorLayout.textView.text = articleUI?.author
        activityArticleDetailsBinding.dateLayout.textView.text = articleUI?.publishAt
        activityArticleDetailsBinding.contentTextView.text = articleUI?.content
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_details_activity_toolbar_action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override val component: ArticleDetailsActivityComponent?
        get() {
            if (articleDetailsActivityComponent == null) {
                initPresenter()
            }
            return articleDetailsActivityComponent
        }

    override fun createPresenter(): ArticleDetailsActivityContract.Presenter {
        initializeInjector()
        return articleDetailsActivityPresenter!!
    }

    private fun initializeInjector() {
        articleDetailsActivityComponent = getApplicationComponent()
            ?.plus(ArticleDetailsActivityModule())
        articleDetailsActivityComponent!!.inject(this)
    }

    override fun releaseComponent() {
        articleDetailsActivityComponent = null
    }

    override fun getViewBinding(): ViewBinding {
        return ActivityArticleDetailsBinding.inflate(layoutInflater)
    }

    fun onSourceButtonClicked() {
    }

    companion object {
        const val EXTRA_ARTICLE = "extra_article"
        fun getCallingIntent(context: Context?, articleUI: ArticleUI?): Intent {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra(EXTRA_ARTICLE, articleUI)
            return intent
        }
    }
}