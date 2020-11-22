package com.said.homework.base.domain.interactor

import io.reactivex.Observable

abstract class UseCase<T, Params> {
    abstract fun build(params: Params): Observable<T>?
}