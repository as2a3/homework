package com.said.homework.base.presentation.di.component

import android.app.Service
import com.said.homework.base.presentation.view.BaseActivity
import com.said.homework.base.presentation.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(baseActivity: BaseActivity?)
    fun inject(syncService: Service?)
//    fun plus(mainActivityModule: MainActivityModule?): MainActivityComponent?

//    operator fun plus(mainActivityModule: MainActivityModule?): MainActivityComponent?
}