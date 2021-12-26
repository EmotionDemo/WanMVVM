package com.lfh.wanmvvm.util

import android.app.Dialog
import android.content.Context
import com.lfh.wanmvvm.R

class DialogUtil {
    private var dialog: Dialog? = null
    fun showDialog(context: Context, isShow: Boolean) {
        cancel()
        dialog = Dialog(context)
        dialog?.setContentView(R.layout.layout_dialog_loading)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    fun cancel() {
        dialog?.cancel()
    }
}