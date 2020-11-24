package com.said.homework.base.presentation.presenter

import com.said.homework.base.presentation.contract.BaseContract
import dagger.internal.Preconditions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BasePresenter<P : BaseContract.View?> : BaseContract.Presenter<P> {
    private val mDisposables = CompositeDisposable()
    private var mViewRef: WeakReference<P?>? = null
    override fun onAttach(view: P) {
        mViewRef = WeakReference(view)
    }

    override fun onResume() {
        // Not mandatory for all views, if views are interested in receiving this event, they should
        // override this method
    }

    override fun onDetach() {
        if (mViewRef != null) {
            mViewRef!!.clear()
            mViewRef = null
        }
        clearDisposable()
    }

    /**
     * @param disposable disposable
     */
    override fun addDisposable(disposable: Disposable?) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(mDisposables)
        disposable?.let { mDisposables.add(it) }
    }

    /**
     * @param disposable disposable
     */
    override fun dispose(disposable: Disposable?) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(mDisposables)
        disposable!!.dispose()
        mDisposables.delete(disposable)
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    protected fun clearDisposable() {
        if (!mDisposables.isDisposed) {
            mDisposables.dispose()
        }
    }

    /**
     * @return True if the view this presenter is attached to still exists and not garbage collected
     * since we are holding it through a `WeakReference`
     */
    protected val isViewAttached: Boolean
        get() = mViewRef != null && mViewRef!!.get() != null
    protected val view: P?
        get() = mViewRef!!.get()
}