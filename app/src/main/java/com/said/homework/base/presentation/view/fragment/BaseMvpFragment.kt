package com.said.homework.base.presentation.view.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.afollestad.materialdialogs.MaterialDialog
import com.said.homework.R
import com.said.homework.base.presentation.contract.BaseContract
import com.said.homework.base.presentation.util.DialogUtils.getProgressDialog

abstract class BaseMvpFragment<T : BaseContract.Presenter<*>?> : BaseFragment(), BaseContract.View {
    private var mPresenter: T? = null
    private var mProgressDialog: MaterialDialog? = null
    protected abstract fun releaseComponent()
    protected val presenter: T
        get() {
            initPresenter()
            return mPresenter!!
        }

    protected fun initPresenter() {
        if (mPresenter == null) mPresenter = createPresenter()
        checkNotNull(mPresenter) { "createPresenter() implementation returns null!" }
    }

    protected abstract fun createPresenter(): T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        initPresenter()
//        presenter!!.onAttach(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initPresenter()
//        presenter!!.onAttach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter!!.onResume()
    }

    override fun onDetach() {
        super.onDetach()
        presenter!!.onDetach()
        releaseComponent()
        mPresenter = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun showBlockingLoading(msg: String?) {
        if (mProgressDialog == null) mProgressDialog = getProgressDialog(
            activity,
            msg, false,
            false, R.style.AppCompat_progress_dialog_style
        )
        if (!mProgressDialog!!.isShowing) mProgressDialog!!.show()
    }

    override fun showBlockingLoading() {
        showBlockingLoading(activity.resources.getString(R.string.loading))
    }

    override fun hideBlockingLoading() {
        if (mProgressDialog != null) mProgressDialog!!.dismiss()
    }

    override fun sessionExpired() {
        TODO("Not yet implemented")
    }
}