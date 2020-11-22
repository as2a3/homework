package com.said.homework.base.presentation.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Ahmed Sa'eed on 20/11/2020.
 */
abstract class BaseFragment : Fragment() {
    lateinit var activity: Activity
    lateinit var baseFragmentViewBinding: ViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(getLayoutResourceId(), container, false)
        baseFragmentViewBinding = getViewBinding(view)!!
        return view
    }

    @LayoutRes
    protected abstract fun getLayoutResourceId(): Int

    protected abstract fun getViewBinding(view: View?): ViewBinding?
}