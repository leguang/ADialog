package cn.itsite.adialog;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;

/**
 * Author：leguang on 2016/10/12 0009 15:49
 * Email：langmanleguang@qq.com
 * <p>
 * 页面状态点击事件监听器。
 */

public interface ADialogListener {

    interface OnDialogConvertListener {

        void convert(BaseViewHolder holder, Dialog dialog);
    }

    interface OnDialogFragmentConvertListener {

        void convert(BaseViewHolder holder, DialogFragment dialog);
    }
}