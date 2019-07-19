package com.app.tic_tac_toe_app.helpers

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.app.tic_tac_toe_app.R

class DialogsHelpers {

    companion object {

        fun DialogCancelable(context: Context,title: String,message: String): AlertDialog{
            return AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setMessage(message)
                .create()
        }

        fun DialogSimpleOkButton(context: Context, title: String, message: String, positiveButton: DialogInterface.OnClickListener): AlertDialog{
            return AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_text,positiveButton)
                .setCancelable(false)
                .create()
        }

        fun DialogSimpleCancelButton(context: Context, title: String, message: String, positiveButton: DialogInterface.OnClickListener): AlertDialog{
            return AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.cancel,positiveButton)
                .setCancelable(false)
                .create()
        }

        fun DialogOkAndCancel(context: Context, title: String, message: String, positiveButton: DialogInterface.OnClickListener, negativeButton: DialogInterface.OnClickListener): AlertDialog {
            return AlertDialog.Builder(context, R.style.DialogTheme)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok_text, positiveButton)
                .setNegativeButton(R.string.cancel, negativeButton)
                .setCancelable(false)
                .create()
        }

    }
}