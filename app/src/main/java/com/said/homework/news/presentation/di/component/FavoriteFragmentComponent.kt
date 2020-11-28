package com.said.homework.news.presentation.di.component

import com.said.homework.base.presentation.di.scope.PerFragment
import com.said.homework.news.presentation.di.module.FavoriteFragmentModule
import com.said.homework.news.presentation.view.fragment.FavoriteFragment
import dagger.Subcomponent

/**
 * Created by Ahmed Sa'eed on 24/11/2020.
 */
@PerFragment
@Subcomponent(modules = [FavoriteFragmentModule::class])
interface FavoriteFragmentComponent {
    fun inject(favoriteFragment: FavoriteFragment?)
}