package com.said.homework.news.domain.interactor;

import com.said.homework.base.domain.interactor.UseCase;
import com.said.homework.news.domain.entity.ArticleEntity;
import com.said.homework.news.domain.repository.NewsRepository;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ahmed Sa'eed on 27,November,2020
 */
public class AddArticleToDBUseCase extends UseCase<Long, ArticleEntity> {
    private final NewsRepository mNewsRepository;
    @Inject
    public AddArticleToDBUseCase(NewsRepository mNewsRepository) {
        this.mNewsRepository = mNewsRepository;
    }

    @Nullable
    @Override
    public Observable<Long> build(ArticleEntity articleEntity) {
        return mNewsRepository.addArticleIntoDB(articleEntity);
    }
}
