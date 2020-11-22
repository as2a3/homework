package com.said.homework

import androidx.multidex.MultiDexApplication
import com.google.firebase.analytics.FirebaseAnalytics
import com.said.homework.base.presentation.di.component.ApplicationComponent
import com.said.homework.base.presentation.di.component.DaggerApplicationComponent
import com.said.homework.base.presentation.di.module.ApplicationModule

/**
 * Created by Ahmed Sa'eed on 15.01.2020.
 */
class MyApp : MultiDexApplication() {
    private var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        initializeInjector()

        // Obtain the FirebaseAnalytics instance.
        fireBaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    companion object {
        private var sInstance: MyApp? = null
        var fireBaseAnalytics: FirebaseAnalytics? = null
            private set

        fun get(): MyApp? {
            checkNotNull(sInstance) { "Something went horribly wrong, no application attached!" }
            return sInstance
        }
    }

    private fun initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.create()
//        mApplicationComponent = DaggerApplicationComponent
//            .builder()
//            .applicationModule(ApplicationModule(this))
//            .build()
    }

    fun getApplicationComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}