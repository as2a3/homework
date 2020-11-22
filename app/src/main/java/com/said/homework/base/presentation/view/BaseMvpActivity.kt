package com.said.homework.base.presentation.view

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
        protected get() {
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
        presenter!!.onAttach(this as Nothing)
    }

    public override fun onResume() {
        super.onResume()
        presenter!!.onResume()
    }

    override fun finish() {
        super.finish()
    }

    override fun onDestroy() {
        presenter!!.onDetach()
        super.onDestroy()
    }

    fun showBlockingLoading(msg: String?) {
        if (mProgressDialog == null) mProgressDialog = DialogUtils.getProgressDialog(
            this,
            msg, false,
            false, R.style.AppCompat_progress_dialog_style
        )
        if (!mProgressDialog!!.isShowing) mProgressDialog!!.show()
    }

    override fun showBlockingLoading() {
        showBlockingLoading(get()!!.resources.getString(R.string.loading))
    }

    override fun hideBlockingLoading() {
        if (mProgressDialog != null) mProgressDialog!!.dismiss()
    }

    override fun sessionExpired() {}
}