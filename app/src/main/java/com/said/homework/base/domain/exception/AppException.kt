package com.said.homework.base.domain.exception

open class AppException(private val throwable: Throwable?) : Exception() {
    private var mMessage: String? = null
    override var message: String?
        get() = mMessage
        set(message) {
            mMessage = message
        }

    init {
        if (throwable != null) mMessage = throwable.message
    }
}