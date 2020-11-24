package com.said.homework.news.presentation.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.ViewBinding
import com.said.homework.R
import com.said.homework.base.presentation.di.HasComponent
import com.said.homework.base.presentation.view.BaseMvpActivity
import com.said.homework.databinding.ActivityMainBinding
import com.said.homework.news.presentation.contract.MainActivityContract
import com.said.homework.news.presentation.di.component.MainActivityComponent
import com.said.homework.news.presentation.di.module.MainActivityModule
import com.said.homework.news.presentation.presenter.MainActivityPresenter
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainActivityContract.Presenter?>(), MainActivityContract.View, HasComponent<MainActivityComponent?> {

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter
    var mainActivityComponent: MainActivityComponent? = null

    lateinit var mainActivityViewBinding: ActivityMainBinding

    override fun getViewBinding(): ViewBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun releaseComponent() {
        mainActivityComponent = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewBinding = baseActivityViewBinding as ActivityMainBinding
        initializeViews()
        presenter?.getNews()
    }

    private fun initializeViews() {
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_favorite))
        setupActionBarWithNavController(navController, appBarConfiguration)
        mainActivityViewBinding.navView.setupWithNavController(navController)
    }

    override fun createPresenter(): MainActivityContract.Presenter {
        initializeInjector()
        return mainActivityPresenter
    }

    override val component: MainActivityComponent
        get() {
            if (mainActivityComponent == null) {
                initPresenter()
            }
            return mainActivityComponent!!
        }

    private fun initializeInjector() {
        mainActivityComponent = getApplicationComponent()
                ?.plus(MainActivityModule())!!
        mainActivityComponent?.inject(this)
    }

    override fun showBlockingLoading() {
        showBlockingLoading(this.resources.getString(R.string.loading))
    }
}