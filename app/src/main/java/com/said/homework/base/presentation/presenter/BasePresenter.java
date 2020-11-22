package com.said.homework.base.presentation.presenter;

import com.said.homework.base.presentation.contract.BaseContract;

import java.lang.ref.WeakReference;

import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<P extends BaseContract.View> implements BaseContract.Presenter<P> {
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    private WeakReference<P> mViewRef;

    @Override
    public void onAttach(P view) {
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void onResume() {
        // Not mandatory for all views, if views are interested in receiving this event, they should
        // override this method
    }

    @Override
    public void onDetach() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }

        clearDisposable();
    }

    /**
     * @param disposable disposable
     */
    @Override
    public void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(mDisposables);
        mDisposables.add(disposable);
    }


    /**
     * @param disposable disposable
     */
    @Override
    public void dispose(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(mDisposables);
        disposable.dispose();
        mDisposables.delete(disposable);
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    protected void clearDisposable() {
        if (!mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    /**
     * @return True if the view this presenter is attached to still exists and not garbage collected
     * since we are holding it through a {@code WeakReference}
     */
    protected boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    protected P getView() {
        return mViewRef.get();
    }

}