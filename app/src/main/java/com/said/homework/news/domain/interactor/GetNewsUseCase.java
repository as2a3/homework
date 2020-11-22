package com.said.homework.news.domain.interactor;

import com.said.homework.base.domain.interactor.UseCase;
import com.said.homework.news.data.model.response.GetNewsResponse;
import com.said.homework.news.domain.entity.GetNewsParamsEntity;
import com.said.homework.news.domain.repository.NewsRepository;

import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ahmed Sa'eed on 22/11/2020.
 */
public class GetNewsUseCase extends UseCase<GetNewsResponse, GetNewsParamsEntity> {

    private final NewsRepository mNewsRepository;

    @Inject
    public GetNewsUseCase(NewsRepository mNewsRepository) {
        this.mNewsRepository = mNewsRepository;
    }

    @Nullable
    @Override
    public Observable<GetNewsResponse> build(GetNewsParamsEntity getNewsParamsEntity) {
        return mNewsRepository.getArticles(getNewsParamsEntity);
    }
}
