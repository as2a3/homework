package com.said.homework.news.domain.interactor;

import com.said.homework.base.domain.interactor.UseCase;
import com.said.homework.news.domain.entity.ArticleEntity;
import com.said.homework.news.domain.repository.NewsRepository;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ahmed Sa'eed on 27/11/2020.
 */
public class GetDBArticlesUseCase extends UseCase<List<ArticleEntity>, Void> {
    private final NewsRepository mNewsRepository;

    @Inject
    public GetDBArticlesUseCase(NewsRepository mNewsRepository) {
        this.mNewsRepository = mNewsRepository;
    }

    @Nullable
    @Override
    public Observable<List<ArticleEntity>> build(Void aVoid) {
        return mNewsRepository.getDBArticles();
    }
}
