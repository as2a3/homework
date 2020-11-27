package com.said.homework.news.domain.interactor;

import com.said.homework.base.domain.interactor.UseCase;
import com.said.homework.news.domain.repository.NewsRepository;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;

public class InitDataBaseUseCase extends UseCase<Boolean, Void> {
    private final NewsRepository mNewsRepository;

    @Inject
    public InitDataBaseUseCase(NewsRepository mNewsRepository) {
        this.mNewsRepository = mNewsRepository;
    }

    @Nullable
    @Override
    public Observable<Boolean> build(Void aVoid) {
        return mNewsRepository.initDatabaseDao();
    }
}