package com.said.homework.news.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.said.homework.AppConstants.DEFAULT_KEYWORD
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

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var noDataLayout: ConstraintLayout

    @Inject
    lateinit var newsAdapter: NewsAdapter
    private var currentPage: Int = ONLINE_PAGE_START_COUNT
    private var loadMore = false
    private val articleUIList: ArrayList<ArticleUI> = ArrayList<ArticleUI>()
    private var getNewsParamsEntity: GetNewsParamsEntity = GetNewsParamsEntity(currentPage, DEFAULT_KEYWORD)
    private var isSearchedData : Boolean = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initializeViews(root)
        presenter?.getRemoteAPIKey(this)
        return root
    }

    private fun initializeViews(view: View?) {
        this.fragmentHomeBinding = baseFragmentViewBinding as FragmentHomeBinding
        noDataLayout = view?.findViewById(R.id.no_data_found_include) as ConstraintLayout

        // Adapter
        newsAdapter.setArticleUIS(articleUIList)

        // Recycler View
        recyclerView = view.findViewById(R.id.news_recyclerView) as RecyclerView
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

        // Search View
        searchView = view.findViewById(R.id.searchView) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                getNewsParamsEntity.page = ONLINE_PAGE_START_COUNT
                getNewsParamsEntity.keyword = newText
                isSearchedData = true
                getNewsPageData()
                return true
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
        if (getNewsParamsEntity.keyword?.isEmpty() == true)
            getNewsParamsEntity.keyword = DEFAULT_KEYWORD
        presenter?.getNews(this, getNewsParamsEntity)
    }

    override fun onGetAPIKey() {
        getFirstNewsPageData()
    }


    override fun onGetNewsSuccessful(newsEntity: NewsEntity) {
        hideBlockingLoading()
        if (currentPage == ONLINE_PAGE_START_COUNT && newsEntity.articleEntities!!.isEmpty()) { // First time
            recyclerView.visibility = View.GONE
            noDataLayout.visibility = View.VISIBLE
            loadMore = false
            currentPage--
        } else if (newsEntity.articleEntities!!.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            noDataLayout.visibility = View.GONE

            if (isSearchedData || currentPage == ONLINE_PAGE_START_COUNT) {
                articleUIList.clear()
                currentPage = ONLINE_PAGE_START_COUNT
            }
            articleUIList.addAll(NewsUIMapper.map(newsEntity).articleUIS)
            loadMore = articleUIList.size < newsEntity.total!!
        } else {
            recyclerView.visibility = View.VISIBLE
            noDataLayout.visibility = View.GONE
            currentPage--
            loadMore = false
        }
        isSearchedData = false
        currentPage += 1
        getNewsParamsEntity.page = currentPage

        newsAdapter.setIsLoadMore(loadMore)
        newsAdapter.setArticleUIS(articleUIList)
        newsAdapter.notifyDataSetChanged()
    }

    override fun onGetNewsFailed(msg: String) {
        hideBlockingLoading()
        if (currentPage != ONLINE_PAGE_START_COUNT) {
            currentPage--
            loadMore = false
            newsAdapter.setIsLoadMore(loadMore)
            newsAdapter.notifyDataSetChanged()
        } else {
            DialogUtils.showRetryLaterDialog(
                    activity,
                    null,
                    msg,
                    getString(R.string.retry_now),
                    { presenter?.getNews(this, getNewsParamsEntity) },
                    getString(R.string.retry_later),
                    null, false)
        }
    }

    override fun onArticleItemSelected(articleUI: ArticleUI?) {
        activity.startActivity(ArticleDetailsActivity.getCallingIntent(activity, articleUI))
    }

}