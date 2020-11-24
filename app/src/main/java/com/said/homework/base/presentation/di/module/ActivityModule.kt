package com.said.homework.base.presentation.di.module

import com.said.homework.base.presentation.view.activity.BaseActivity
import com.said.homework.base.presentation.di.scope.PerActivity
import dagger.Module
import dagger.Provides

/**
 * A module to wrap the Activity state and expose it to the graph.
 * used when we need to inject the activity inside any class within this component
 */
@Module
class ActivityModule(private val activity: BaseActivity) {
    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    fun activity(): BaseActivity {
        return activity
    }
}