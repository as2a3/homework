package com.said.homework.base.data.network;

import com.said.homework.base.data.exception.RetrofitException;
import com.said.homework.base.presentation.util.CrashUtil;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {
    private final RxJava2CallAdapterFactory mOriginalCallAdapterFactory;

    private RxErrorHandlingCallAdapterFactory() {
        mOriginalCallAdapterFactory = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(final Type returnType, final Annotation[] annotations, final Retrofit retrofit) {
        return new RxCallAdapterWrapper<>(mOriginalCallAdapterFactory.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper<R> implements CallAdapter<R, Observable<R>> {
        private final CallAdapter<R, ?> mWrappedCallAdapter;

        RxCallAdapterWrapper(final CallAdapter<R, ?> wrapped) {
            mWrappedCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrappedCallAdapter.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Observable<R> adapt(final Call<R> call) {
            return ((Observable) mWrappedCallAdapter.adapt(call)).onErrorResumeNext(new Function<Throwable, ObservableSource>() {
                @Override
                public Observable apply(final Throwable throwable) {
                    return Observable.error(asRetrofitException(throwable));
                }
            });
        }

        private RetrofitException asRetrofitException(final Throwable throwable) {
            throwable.printStackTrace();
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                final HttpException httpException = (HttpException) throwable;
                final Response response = httpException.response();

                return RetrofitException.Companion.httpError(response);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RetrofitException.Companion.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error
            CrashUtil.INSTANCE.logFireBaseCrash(CrashUtil.RX_JAVA_EXCEPTION, throwable.getMessage());
            return RetrofitException.Companion.unexpectedError(throwable);
        }
    }
}