package com.said.homework.news.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.said.homework.R
import com.said.homework.base.presentation.view.fragment.BaseFragment
import com.said.homework.base.presentation.view.fragment.BaseMvpFragment
import com.said.homework.databinding.FragmentHomeBinding
import com.said.homework.news.domain.entity.NewsEntity
import com.said.homework.news.presentation.contract.HomeFragmentContract
import com.said.homework.news.presentation.presenter.HomeFragmentPresenter
import com.said.homework.news.presentation.view.viewmodel.HomeViewModel

class HomeFragment : BaseMvpFragment<HomeFragmentContract.Presenter?>(),
        HomeFragmentContract.View {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_home
    }

    override fun getViewBinding(view: View?): ViewBinding? {
        return view?.let { FragmentHomeBinding.bind(it) }
    }

    override fun releaseComponent() {
        TODO("Not yet implemented")
    }

    override fun createPresenter(): HomeFragmentContract.Presenter? {
        TODO("Not yet implemented")
    }

    override fun onGetNewsSuccessful(newsEntity: NewsEntity) {
        TODO("Not yet implemented")
    }

    override fun onGetNewsFailed(msg: String) {
        TODO("Not yet implemented")
    }

}