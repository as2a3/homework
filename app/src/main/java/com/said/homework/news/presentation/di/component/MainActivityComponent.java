package com.said.homework.news.presentation.di.component;

import com.said.homework.base.presentation.di.scope.PerActivity;
import com.said.homework.news.presentation.di.module.MainActivityModule;
import com.said.homework.news.presentation.ui.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Ahmed Sa'eed on 23/11/2020.
 */
@PerActivity
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
