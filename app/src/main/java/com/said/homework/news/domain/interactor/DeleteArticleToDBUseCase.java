package com.said.homework.news.domain.interactor;

import com.said.homework.base.domain.interactor.UseCase;
import com.said.homework.news.domain.entity.ArticleEntity;
import com.said.homework.news.domain.repository.NewsRepository;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ahmed Sa'eed on 27/11/2020.
 */
public class DeleteArticleToDBUseCase extends UseCase<Boolean, ArticleEntity> {
    private final NewsRepository mNewsRepository;
    @Inject
    public DeleteArticleToDBUseCase(NewsRepository mNewsRepository) {
        this.mNewsRepository = mNewsRepository;
    }

    @Nullable
    @Override
    public Observable<Boolean> build(ArticleEntity articleEntity) {
        return mNewsRepository.deleteArticleFromDB(articleEntity);
    }
}
