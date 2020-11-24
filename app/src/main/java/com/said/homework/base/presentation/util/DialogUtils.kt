package com.said.homework.base.presentation.util

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.text.TextUtils
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.said.homework.R
import java.lang.reflect.Method

object DialogUtils {
    fun showToast(context: Context,
        @StringRes messageRes: Int) {
        Toast.makeText(context, messageRes, Toast.LENGTH_LONG).show()
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showOkDialog(context: Context, title: String, msg: String?,
                     okTxt: String, okAction: Runnable?, cancelable: Boolean): Dialog {
        return showOkCancelNeutralDialog(
            context, title, msg, okTxt, okAction, null, null,
            null, null, cancelable)
    }

    fun showOkDialog(context: Context, title: String?, msg: String?,
                     okTxt: String?, okAction: Runnable?, cancelable: Boolean, style: Int): Dialog {
        return showOkCancelNeutralDialogWithCustomStyle(
            context,
            title,
            msg,
            okTxt,
            okAction,
            null,
            null,
            null,
            null,
            cancelable,
            style
        )
    }

    fun showOkCancelDialog(
        context: Context,
        title: String,
        msg: String?,
        okTxt: String,
        okAction: Runnable?,
        cancelTxt: String?,
        cancelAction: Runnable?,
        cancelable: Boolean
    ): Dialog {
        return showOkCancelNeutralDialog(
            context,
            title,
            msg,
            okTxt,
            okAction,
            null,
            null,
            cancelTxt,
            cancelAction,
            cancelable
        )
    }

    fun showOkCancelDialog(
        context: Context,
        title: String?,
        msg: String?,
        okTxt: String?,
        okAction: Runnable?,
        cancelTxt: String?,
        cancelAction: Runnable?,
        cancelable: Boolean,
        style: Int
    ): Dialog {
        return showOkCancelNeutralDialogWithCustomStyle(
            context,
            title,
            msg,
            okTxt,
            okAction,
            null,
            null,
            cancelTxt,
            cancelAction,
            cancelable,
            style
        )
    }

    private fun showOkCancelNeutralDialog(
        context: Context,
        title: String,
        msg: String?,
        okTxt: String,
        okAction: Runnable?,
        neutralTxt: String?,
        neutralAction: Runnable?,
        cancelTxt: String?,
        cancelAction: Runnable?,
        cancelable: Boolean
    ): Dialog {
        val builder = AlertDialog.Builder(
            context,
            if (TextUtils.isEmpty(title)) R.style.AppCompat_alert_dialog_style_no_title else R.style.AppCompat_alert_dialog_style_with_title
        )

//
        if (!TextUtils.isEmpty(title)) {
            @SuppressLint("InflateParams") val textCustomTitle = LayoutInflater
                .from(context).inflate(R.layout.dialog_custom_title, null, false) as TextView
            textCustomTitle.text = title
            builder.setCustomTitle(textCustomTitle)
        }
        builder.setMessage(msg)
        if (!TextUtils.isEmpty(okTxt)) {
            builder.setPositiveButton(okTxt) { dialog: DialogInterface, which: Int ->
                okAction?.run()
                dialog.dismiss()
            }
        }
        if (!TextUtils.isEmpty(neutralTxt)) {
            builder.setPositiveButton(neutralTxt) { dialog: DialogInterface, which: Int ->
                neutralAction?.run()
                dialog.dismiss()
            }
        }
        if (!TextUtils.isEmpty(cancelTxt)) {
            builder.setNegativeButton(cancelTxt) { dialogInterface: DialogInterface, i: Int ->
                cancelAction?.run()
                dialogInterface.dismiss()
            }
        }
        builder.setCancelable(cancelable)
        return builder.show()
    }

    fun showOkCancelNeutralDialogWithCustomStyle(
        context: Context,
        title: String?,
        msg: String?,
        okTxt: String?,
        okAction: Runnable?,
        neutralTxt: String?,
        neutralAction: Runnable?,
        cancelTxt: String?,
        cancelAction: Runnable?,
        cancelable: Boolean,
        style: Int
    ): Dialog {
        val builder = AlertDialog.Builder(context, style)
        if (!TextUtils.isEmpty(title)) {
            @SuppressLint("InflateParams") val textCustomTitle = LayoutInflater
                .from(context).inflate(R.layout.dialog_custom_title, null, false) as TextView
            textCustomTitle.text = title
            builder.setCustomTitle(textCustomTitle)
        }
        builder.setMessage(msg)
        if (!TextUtils.isEmpty(okTxt)) {
            builder.setPositiveButton(okTxt) { dialog: DialogInterface, which: Int ->
                okAction?.run()
                dialog.dismiss()
            }
        }
        if (!TextUtils.isEmpty(neutralTxt)) {
            builder.setPositiveButton(neutralTxt) { dialog: DialogInterface, which: Int ->
                neutralAction?.run()
                dialog.dismiss()
            }
        }
        if (!TextUtils.isEmpty(cancelTxt)) {
            builder.setNegativeButton(cancelTxt) { dialogInterface: DialogInterface, i: Int ->
                cancelAction?.run()
                dialogInterface.dismiss()
            }
        }
        builder.setCancelable(cancelable)
        return builder.show()
    }

    fun showRetryLaterDialog(
        context: Context,
        title: String?,
        msg: String?,
        retryTxt: String?,
        retryAction: Runnable?,
        laterTxt: String?,
        laterAction: Runnable?,
        cancelable: Boolean
    ) {
        showRetryLaterNeutralDialog(
            context, title, msg, retryTxt, retryAction,
            null, null, laterTxt, laterAction, cancelable
        )
    }

    fun showRetryLaterNeutralDialog(
        context: Context,
        title: String?,
        msg: String?,
        retryTxt: String?,
        retryAction: Runnable?,
        neutralTxt: String?,
        neutralAction: Runnable?,
        laterTxt: String?,
        laterAction: Runnable?,
        cancelable: Boolean
    ) {
        val builder = AlertDialog.Builder(
            context,
//            if (TextUtils.isEmpty(title)) R.style.AppCompat_alert_dialog_style_no_title else R.style.AppCompat_alert_dialog_style_with_title
        )
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.setMessage(msg)
        if (!TextUtils.isEmpty(retryTxt)) {
            builder.setPositiveButton(retryTxt) { dialog: DialogInterface, which: Int ->
                retryAction?.run()
                dialog.dismiss()
            }
        }
        if (!TextUtils.isEmpty(neutralTxt)) {
            builder.setPositiveButton(neutralTxt) { dialog: DialogInterface, which: Int ->
                neutralAction?.run()
                dialog.dismiss()
            }
        }
        if (!TextUtils.isEmpty(laterTxt)) {
            builder.setNegativeButton(laterTxt) { dialogInterface: DialogInterface, i: Int ->
                laterAction?.run()
                dialogInterface.dismiss()
            }
        }
        builder.setCancelable(cancelable)
        builder.show()
    }

    /**
     * @param context - Context
     * @param message - String message
     * @param canCancelable - boolean canCancelable
     * @param canceledOnTouchOutside - boolean canceledOnTouchOutside
     * @param style                  like R.style.AppCompat_alert_dialog_style_no_title
     * @return Material dialog
     */
    @JvmStatic
    fun getProgressDialog(
        context: Context?, message: String?, canCancelable: Boolean,
        canceledOnTouchOutside: Boolean, style: Int
    ): MaterialDialog {
        return MaterialDialog.Builder(ContextThemeWrapper(context, style))
            .title("")
            .content(message?: "")
            .cancelable(canCancelable)
            .autoDismiss(canceledOnTouchOutside)
            .progress(true, 0).build()
    }

    /**
     * @param context        context to which dialog will be displayed
     * @param msg            Dialog message (Message to which user responds)
     * @param okTxtResId     Resource id for text displayed on positive, i.e. OK button.
     * @param cancelTxtResId Resource id for text displayed on negative, i.e. Cancel button.
     * @param okAction       Action to perform if ok is pressed. Can not be null. Dialog is dismissed after click
     * @param cancelAction   Action to perform if cancel is pressed. If null, dialog will be dismissed when cancel is pressed
     * @author Rowan
     */
    fun showCancelableDialog(
        context: Context, msg: String,
        okTxtResId: Int, cancelTxtResId: Int,
        okAction: Runnable, cancelAction: Runnable?
    ) {
        val builder = AlertDialog.Builder(
            context,
            R.style.AppCompat_alert_dialog_custom_negative_button_style
        )
            .setMessage(msg)
            .setPositiveButton(okTxtResId) { dialog: DialogInterface, which: Int ->
                okAction.run()
                dialog.dismiss()
            }
            .setNegativeButton(cancelTxtResId) { dialog: DialogInterface, which: Int ->
                cancelAction?.run()
                dialog.dismiss()
            }
        builder.show()
    }

    fun forceShowMenuItemsIcons(popup: PopupMenu) {
        try {
            val fields = popup.javaClass.declaredFields
            for (field in fields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field[popup]
                    var classPopupHelper: Class<*>? = null
                    if (menuPopupHelper != null) {
                        classPopupHelper = Class.forName(
                            menuPopupHelper
                                .javaClass.name
                        )
                    }
                    var setForceIcons: Method? = null
                    if (classPopupHelper != null) {
                        setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", Boolean::class.javaPrimitiveType
                        )
                    }
                    setForceIcons?.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun getSnackBar(
        view: View?, textMsg: CharSequence?, @BaseTransientBottomBar.Duration duration: Int,
        textOfOnClick: String?, onClickListener: View.OnClickListener?
    ): Snackbar {
        val snackbar = Snackbar.make(view!!, textMsg!!, duration)
        val snackBarView = snackbar.view
        val textMessage =
            snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textMessage.setTextColor(Color.WHITE)
        if (onClickListener != null) {
            snackbar.setAction(textOfOnClick, onClickListener)
            snackbar.setActionTextColor(Color.RED)
        }
        return snackbar
    }
}