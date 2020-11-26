package com.said.homework.news.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.said.homework.AppConstants.ONLINE_PAGE_START_COUNT
import com.said.homework.R
import com.said.homework.base.presentation.util.DialogUtils
import com.said.homework.base.presentation.view.fragment.BaseMvpFragment
import com.said.homework.base.presentation.view.listener.RecyclerPaginationScrollListener
import com.said.homework.databinding.FragmentHomeBinding
import com.said.homework.news.domain.entity.GetNewsParamsEntity
import com.said.homework.news.domain.entity.NewsEntity
import com.said.homework.news.presentation.contract.HomeFragmentContract
import com.said.homework.news.presentation.di.component.HomeFragmentComponent
import com.said.homework.news.presentation.di.module.HomeFragmentModule
import com.said.homework.news.presentation.model.ArticleUI
import com.said.homework.news.presentation.model.mapper.NewsUIMapper
import com.said.homework.news.presentation.presenter.HomeFragmentPresenter
import com.said.homework.news.presentation.view.activity.ArticleDetailsActivity
import com.said.homework.news.presentation.view.activity.MainActivity
import com.said.homework.news.presentation.view.adapter.NewsAdapter
import java.util.*
import javax.inject.Inject

class HomeFragment : BaseMvpFragment<HomeFragmentContract.Presenter?>(),
        HomeFragmentContract.View, NewsAdapter.Callback {

    @Inject
    lateinit var homeFragmentPresenter: HomeFragmentPresenter
    private var homeFragmentComponent: HomeFragmentComponent? = null

    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private var currentPage: Int = ONLINE_PAGE_START_COUNT
    private var loadMore = false
    private val articleUIList: ArrayList<ArticleUI> = ArrayList<ArticleUI>()
    private var getNewsParamsEntity: GetNewsParamsEntity = GetNewsParamsEntity(currentPage, "besiktas")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initializeViews(root)
        getFirstNewsPageData()
        return root
    }

    private fun initializeViews(view: View?) {
        fragmentHomeBinding = baseFragmentViewBinding as FragmentHomeBinding

        // Adapter
        newsAdapter.setArticleUIS(articleUIList)

        // Views
        recyclerView = view?.findViewById(R.id.news_recyclerView) as RecyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, activity.resources.getInteger(R.integer.news_span_count))
        recyclerView.adapter = newsAdapter
        newsAdapter.setListener(this)
        recyclerView.addOnScrollListener(object :
            RecyclerPaginationScrollListener() {
            override fun onScrollToDown() {}
            override fun onScrollToTop() {}
            override fun onLoadMore() {
                if (loadMore) {
                    loadMore = false
                    getNewsPageData()
                }
            }
        })
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewBinding(view: View?): ViewBinding {
        return view?.let { FragmentHomeBinding.bind(it) }!!
    }

    override fun releaseComponent() {
        homeFragmentComponent = null
    }

    override fun createPresenter(): HomeFragmentContract.Presenter {
        initializeInjector()
        return homeFragmentPresenter
    }

    private fun initializeInjector() {
        homeFragmentComponent = (activity as MainActivity).component.plus(HomeFragmentModule())
        homeFragmentComponent!!.inject(this)
    }


    /****** Get Data ******/
    private fun getFirstNewsPageData() {
        showBlockingLoading()
        getNewsPageData()
    }

    private fun getNewsPageData() {
        presenter?.getNews(this, getNewsParamsEntity)
    }


    override fun onGetNewsSuccessful(newsEntity: NewsEntity) {
        hideBlockingLoading()
        if (currentPage == ONLINE_PAGE_START_COUNT && newsEntity.articleEntities!!.isEmpty()) { // First time
            fragmentHomeBinding.newsRecyclerView.visibility = View.GONE
//            fragmentHomeBinding.noDataFoundLayout.getRoot().setVisibility(View.VISIBLE)
            loadMore = false
            currentPage--
        } else if (newsEntity.articleEntities!!.isNotEmpty()) {
            articleUIList.addAll(NewsUIMapper.map(newsEntity).articleUIS)
            loadMore = articleUIList.size < newsEntity.total!!
        } else {
            currentPage--
            loadMore = false
        }
        currentPage += 1
        getNewsParamsEntity.page = currentPage

        newsAdapter.setIsLoadMore(loadMore)
        newsAdapter.setArticleUIS(articleUIList)
        newsAdapter.notifyDataSetChanged()
    }

    override fun onGetNewsFailed(msg: String) {
        DialogUtils.showRetryLaterDialog(
            activity,
            null,
            msg,
            getString(R.string.retry_now),
            { presenter?.getNews(this, getNewsParamsEntity) },
            getString(R.string.retry_later),
            null, false
        )
    }

    override fun onArticleItemSelected(articleUI: ArticleUI?) {
        activity.startActivity(ArticleDetailsActivity.getCallingIntent(activity, articleUI))
    }

}