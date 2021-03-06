package cn.itsite.adialog.support;

import android.app.Dialog;
import android.view.View;

import androidx.fragment.app.DialogFragment;

import cn.itsite.adialog.common.BaseViewHolder;

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

//    interface OnBottomSheetDialogConvertListener {
//
//        void convert(BaseViewHolder holder, BottomSheetDialog dialog);
//    }
//
//    interface OnBottomSheetDialogFragmentConvertListener {
//
//        void convert(BaseViewHolder holder, BottomSheetDialogFragment dialog);
//    }

    interface OnItemConvertListener {

        void onItemConvert(BaseViewHolder holder, int position, Dialog dialog);
    }

    interface OnItemClickListener {

        void onItemClick(View v, BaseViewHolder holder, int position, Dialog dialog);
    }
}