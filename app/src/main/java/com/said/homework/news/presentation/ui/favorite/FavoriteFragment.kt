package com.said.homework.news.presentation.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.said.homework.R
import com.said.homework.base.presentation.view.BaseFragment
import com.said.homework.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel =
                ViewModelProvider(this).get(FavoriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        favoriteViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_favorite
    }

    override fun getViewBinding(view: View?): ViewBinding? {
        return view?.let { FragmentFavoriteBinding.bind(it) }
    }
}