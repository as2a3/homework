package com.said.homework.base.data.database

import io.reactivex.Observable

interface BaseDatabaseCrud<T> {
    fun initDAOs(): Observable<Boolean?>?
    fun addOrUpdate(t: T): Observable<Long?>?
    fun update(t: T): Observable<Long?>?
    fun delete(t: T): Observable<Boolean?>?
    fun getByLocalId(localId: Long): Observable<T>?
    fun getByCloudId(cloudId: Long): Observable<T>?
    val all: Observable<List<T>?>?
}