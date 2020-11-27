package com.said.homework.news.presentation.di.module

import com.said.homework.base.presentation.di.scope.PerActivity
import com.said.homework.news.data.db.ArticleDbManager
import com.said.homework.news.data.network.NewsManagementCloud
import com.said.homework.news.data.repository.NewsRepositoryImp
import com.said.homework.news.domain.interactor.AddArticleToDBUseCase
import com.said.homework.news.domain.interactor.DeleteArticleToDBUseCase
import com.said.homework.news.domain.interactor.GetDBArticlesUseCase
import com.said.homework.news.domain.interactor.InitDataBaseUseCase
import com.said.homework.news.domain.repository.NewsRepository
import com.said.homework.news.presentation.presenter.ArticleDetailsActivityPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmed Sa'eed on 26/11/2020.
 */
@Module
class ArticleDetailsActivityModule {
    @Provides
    @PerActivity
    fun providesNewsRepository(newsManagementCloud: NewsManagementCloud?, articleDbManager: ArticleDbManager?): NewsRepository {
        return NewsRepositoryImp(newsManagementCloud!!, articleDbManager!!)
    }

    @Provides
    @PerActivity
    fun providesArticleDetailsActivityPresenter(addArticleToDBUseCase: AddArticleToDBUseCase,
                                                initDataBaseUseCase: InitDataBaseUseCase,
                                                getDBArticlesUseCase: GetDBArticlesUseCase,
                                                deleteArticleToDBUseCase: DeleteArticleToDBUseCase): ArticleDetailsActivityPresenter {
        return ArticleDetailsActivityPresenter(addArticleToDBUseCase,
            initDataBaseUseCase, getDBArticlesUseCase, deleteArticleToDBUseCase)
    }
}