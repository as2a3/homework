package com.said.homework.base.presentation.view.activity

import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.said.homework.MyApp.Companion.get
import com.said.homework.R
import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.base.presentation.util.DialogUtils

abstract class BaseMvpActivity<T : BaseContract.Presenter<*>?> : BaseActivity(), BaseContract.View {
    private var mPresenter: T? = null
    private var mProgressDialog: MaterialDialog? = null
    protected val presenter: T?
        get() {
            initPresenter()
            return mPresenter
        }

    protected fun initPresenter() {
        if (mPresenter == null) mPresenter = createPresenter()
        checkNotNull(mPresenter) { "createPresenter() implementation returns null!" }
    }

    protected abstract fun createPresenter(): T
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
//        presenter!!.onAttach(this as Nothing)
    }

    public override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun finish() {
        super.finish()
    }

    override fun onDestroy() {
        presenter!!.onDetach()
        super.onDestroy()
    }

    open fun showBlockingLoading(msg: String?) {
        if (mProgressDialog == null) mProgressDialog = DialogUtils.getProgressDialog(
            this,
            msg, false,
            false, R.style.AppCompat_progress_dialog_style
        )
        if (!mProgressDialog!!.isShowing) mProgressDialog!!.show()
    }

    open override fun showBlockingLoading() {
        showBlockingLoading(get()!!.resources.getString(R.string.loading))
    }

    open override fun hideBlockingLoading() {
        if (mProgressDialog != null) mProgressDialog!!.dismiss()
    }

    open override fun sessionExpired() {
        TODO("Not yet implemented")
    }
}