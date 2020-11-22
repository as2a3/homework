package com.said.homework.base.presentation.contract

import io.reactivex.disposables.Disposable

interface BaseContract {
    interface View {
        fun showBlockingLoading()
        fun hideBlockingLoading()
        fun sessionExpired()
    }

    interface Presenter<P : View?> {
        /**
         * Called when an `MvpView` is attached to this presenter.
         *
         * @param view The attached `MvpView`
         */
        fun onAttach(view: P)

        /**
         * Called when the View is resumed according to Android components
         * NOTE: this method will only be called for presenters that override it.
         */
        fun onResume()

        /**
         * Called when an `MvpView` is detached from this presenter.
         */
        fun onDetach()

        /**
         * Called when an user needs to remove specific disposable from list.
         */
        fun dispose(disposable: Disposable?)
        fun addDisposable(disposable: Disposable?)
    }
}