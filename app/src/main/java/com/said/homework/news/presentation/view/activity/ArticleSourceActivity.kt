package com.said.homework.news.presentation.view.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.viewbinding.ViewBinding
import com.said.homework.base.presentation.di.HasComponent
import com.said.homework.base.presentation.view.activity.BaseMvpActivity
import com.said.homework.databinding.ActivityArticleSourceBinding
import com.said.homework.news.presentation.contract.ArticleSourceActivityContract
import com.said.homework.news.presentation.di.component.ArticleSourceActivityComponent
import com.said.homework.news.presentation.di.module.ArticleSourceActivityModule
import com.said.homework.news.presentation.model.ArticleUI
import com.said.homework.news.presentation.presenter.ArticleSourceActivityPresenter
import java.util.*
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 26,November,2020
 */
class ArticleSourceActivity : BaseMvpActivity<ArticleSourceActivityContract.Presenter?>(),
        ArticleSourceActivityContract.View, HasComponent<ArticleSourceActivityComponent?> {

    @Inject
    lateinit var articleSourceActivityPresenter: ArticleSourceActivityPresenter
    var articleSourceActivityComponent: ArticleSourceActivityComponent? = null
    private var articleUI: ArticleUI? = null

    lateinit var activityArticleSourceBinding: ActivityArticleSourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityArticleSourceBinding = baseActivityViewBinding as ActivityArticleSourceBinding
        if (Objects.requireNonNull(intent.extras)!!.containsKey(EXTRA_ARTICLE)) {
            articleUI = intent.getSerializableExtra(EXTRA_ARTICLE) as ArticleUI?
        }
        initializeViews()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initializeViews() {
        this.title = ""
        activityArticleSourceBinding.webView.webViewClient = WebViewClient()
        activityArticleSourceBinding.webView.loadUrl(articleUI?.url.toString())
        val webSettings = activityArticleSourceBinding.webView.settings
        webSettings.javaScriptEnabled = true
    }

    override fun onBackPressed() {
        if (activityArticleSourceBinding.webView.canGoBack()) {
            activityArticleSourceBinding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun createPresenter(): ArticleSourceActivityContract.Presenter? {
        initializeInjector()
        return articleSourceActivityPresenter
    }

    private fun initializeInjector() {
        articleSourceActivityComponent = getApplicationComponent()
                ?.plus(ArticleSourceActivityModule())
        articleSourceActivityComponent!!.inject(this)
    }

    override fun releaseComponent() {
        articleSourceActivityComponent = null
    }

    override fun getViewBinding(): ViewBinding {
        return ActivityArticleSourceBinding.inflate(layoutInflater)
    }

    override val component: ArticleSourceActivityComponent?
        get() {
            if (articleSourceActivityComponent == null) {
                initPresenter()
            }
            return articleSourceActivityComponent
        }

    companion object {
        private const val EXTRA_ARTICLE = "extra_article"
        fun getCallingIntent(context: Context?, articleUI: ArticleUI?): Intent {
            val intent = Intent(context, ArticleSourceActivity::class.java)
            intent.putExtra(EXTRA_ARTICLE, articleUI)
            return intent
        }
    }
}