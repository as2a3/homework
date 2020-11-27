package com.said.homework.news.presentation.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.said.homework.news.presentation.model.mapper.ArticleUIMapper
import com.said.homework.news.presentation.presenter.ArticleDetailsActivityPresenter
import javax.inject.Inject

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
class ArticleDetailsActivity : BaseMvpActivity<ArticleDetailsActivityContract.Presenter?>(),
    ArticleDetailsActivityContract.View, HasComponent<ArticleDetailsActivityComponent?> {

    @Inject
    lateinit var articleDetailsActivityPresenter: ArticleDetailsActivityPresenter
    private var articleDetailsActivityComponent: ArticleDetailsActivityComponent? = null
    private var articleUI: ArticleUI? = null

    private lateinit var activityArticleDetailsBinding: ActivityArticleDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityArticleDetailsBinding = baseActivityViewBinding as ActivityArticleDetailsBinding
        activityArticleDetailsBinding.callback = this
        if (intent.extras?.containsKey(EXTRA_ARTICLE) == true) {
            articleUI = intent.getSerializableExtra(EXTRA_ARTICLE) as ArticleUI?
        }
        initializeViews()
    }

    private fun initializeViews() {
        ImageLoader.loadImage(
            this,
            articleUI!!.urlToImage,
            activityArticleDetailsBinding.articleImageView
        )
        activityArticleDetailsBinding.titleTextView.text = articleUI?.title
        activityArticleDetailsBinding.authorLayout.textView.text = articleUI?.author
        activityArticleDetailsBinding.dateLayout.textView.text = articleUI?.publishAt
        activityArticleDetailsBinding.contentTextView.text = articleUI?.content
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_details_activity_toolbar_action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share) {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Send Transfer Info")
            sharingIntent.putExtra(Intent.EXTRA_TEXT, articleUI?.url)
            startActivity(Intent.createChooser(sharingIntent, "Share text via"))
            return true
        }
        if (item.itemId == R.id.action_favorite) {
            presenter?.addArticleToDB(this, ArticleUIMapper.map(articleUI!!))
        }
        return super.onOptionsItemSelected(item)
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
        return articleDetailsActivityPresenter
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
        startActivity(ArticleSourceActivity.getCallingIntent(this, articleUI))
    }

    companion object {
        private const val EXTRA_ARTICLE = "extra_article"
        fun getCallingIntent(context: Context?, articleUI: ArticleUI?): Intent {
            val intent = Intent(context, ArticleDetailsActivity::class.java)
            intent.putExtra(EXTRA_ARTICLE, articleUI)
            return intent
        }
    }

    override fun onAddArticleToDBSuccess(localID: Long) {
        TODO("Not yet implemented")
    }

    override fun onAddArticleToDBFailed(msg: String) {
        TODO("Not yet implemented")
    }
}