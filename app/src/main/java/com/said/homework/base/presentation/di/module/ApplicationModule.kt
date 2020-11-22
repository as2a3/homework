package com.said.homework.base.presentation.di.module

import android.content.Context
import com.said.homework.base.data.network.BaseNetwork
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val mContext: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return mContext
    }

    @Provides
    @Singleton
    fun providesBaseNetwork(): BaseNetwork? {
        return BaseNetwork()
    }
}