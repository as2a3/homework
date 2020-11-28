package com.said.homework.news.presentation.view.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.said.homework.AppConstants
import com.said.homework.R
import com.said.homework.base.presentation.util.DialogUtils
import com.said.homework.base.presentation.view.fragment.BaseFragment
import com.said.homework.base.presentation.view.fragment.BaseMvpFragment
import com.said.homework.base.presentation.view.listener.RecyclerPaginationScrollListener
import com.said.homework.databinding.FragmentFavoriteBinding
import com.said.homework.databinding.FragmentHomeBinding
import com.said.homework.news.domain.entity.ArticleEntity
import com.said.homework.news.domain.entity.NewsEntity
import com.said.homework.news.presentation.contract.FavoriteFragmentContract
import com.said.homework.news.presentation.contract.HomeFragmentContract
import com.said.homework.news.presentation.di.component.FavoriteFragmentComponent
import com.said.homework.news.presentation.di.component.HomeFragmentComponent
import com.said.homework.news.presentation.di.module.FavoriteFragmentModule
import com.said.homework.news.presentation.di.module.HomeFragmentModule
import com.said.homework.news.presentation.model.ArticleUI
import com.said.homework.news.presentation.model.mapper.ArticleUIMapper
import com.said.homework.news.presentation.presenter.FavoriteFragmentPresenter
import com.said.homework.news.presentation.presenter.HomeFragmentPresenter
import com.said.homework.news.presentation.view.activity.ArticleDetailsActivity
import com.said.homework.news.presentation.view.activity.MainActivity
import com.said.homework.news.presentation.view.adapter.NewsAdapter
import com.said.homework.news.presentation.view.holder.FavoriteViewModel
import java.util.ArrayList
import javax.inject.Inject

class FavoriteFragment : BaseMvpFragment<FavoriteFragmentContract.Presenter?>(),
    FavoriteFragmentContract.View, NewsAdapter.Callback {

    @Inject
    lateinit var favoriteFragmentPresenter: FavoriteFragmentPresenter
    private var favoriteFragmentComponent: FavoriteFragmentComponent? = null

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private val articleUIList: ArrayList<ArticleUI> = ArrayList<ArticleUI>()

    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var noDataLayout: ConstraintLayout

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        initializeViews(root)
//        getMyFavorites()
        return root
    }

    private fun initializeViews(view: View?) {
        this.fragmentFavoriteBinding = baseFragmentViewBinding as FragmentFavoriteBinding
        noDataLayout = view?.findViewById(R.id.no_data_found_include) as ConstraintLayout

        // Adapter
        newsAdapter.setArticleUIS(articleUIList)

        // Recycler View
        recyclerView = view.findViewById(R.id.news_recyclerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, activity.resources.getInteger(R.integer.news_span_count))
        recyclerView.adapter = newsAdapter
        newsAdapter.setListener(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_favorite
    }

    override fun getViewBinding(view: View?): ViewBinding? {
        return view?.let { FragmentFavoriteBinding.bind(it) }
    }

    override fun releaseComponent() {
        favoriteFragmentComponent = null
    }

    override fun createPresenter(): FavoriteFragmentContract.Presenter? {
        initializeInjector()
        return favoriteFragmentPresenter
    }

    private fun initializeInjector() {
        favoriteFragmentComponent = (activity as MainActivity).component.plus(FavoriteFragmentModule())
        favoriteFragmentComponent!!.inject(this)
    }

    override fun onResume() {
        super.onResume()
        getMyFavorites()
    }

    /************ Data ***********/
    private fun getMyFavorites() {
        showBlockingLoading()
        presenter?.initDaoSessions(this)
    }

    override fun onGetFavoriteArticlesSuccessful(articleEntities: MutableCollection<ArticleEntity>) {
        hideBlockingLoading()
        if (articleEntities.isEmpty()) {
            recyclerView.visibility = View.GONE
            noDataLayout.visibility = View.VISIBLE
        } else {
            articleUIList.clear()
            articleUIList.addAll(ArticleUIMapper.map(articleEntities))
            noDataLayout.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE

            newsAdapter.setIsLoadMore(false)
            newsAdapter.setArticleUIS(articleUIList)
            newsAdapter.notifyDataSetChanged()
        }
    }

    override fun onGetFavoriteArticleFailed(msg: String) {
        DialogUtils.showRetryLaterDialog(activity, null, msg,
            resources.getString(R.string.retry_now), this::getMyFavorites,
            resources.getString(R.string.retry_later), null, true)
    }

    override fun onArticleItemSelected(articleUI: ArticleUI?) {
        activity.startActivity(ArticleDetailsActivity.getCallingIntent(activity, articleUI))
    }
}