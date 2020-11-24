package com.said.homework.base.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.said.homework.MyApp
import com.said.homework.base.presentation.di.component.ApplicationComponent

/**
 * Created by Ahmed Sa'eed on 20/11/2020.
 */
abstract class BaseActivity : AppCompatActivity() {
    protected abstract fun releaseComponent()

    lateinit var baseActivityViewBinding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        getApplicationComponent()!!.inject(this)
        baseActivityViewBinding = getViewBinding()
        setContentView(baseActivityViewBinding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseComponent()
    }

    protected abstract fun getViewBinding(): ViewBinding

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
    }

    open fun getApplicationComponent(): ApplicationComponent? {
        return (application as MyApp).getApplicationComponent()
    }
//    open fun onAttach(context: Context) {
//        super.onAttach(context)
//        try {
//            activity = context as Activity
//        } catch (e: ClassCastException) {
//            throw ClassCastException(
//                context.toString()
//                        + " must be instance of Activity"
//            )
//        }
//    }
}