package cn.itsite.adialog.support.dialog;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import cn.itsite.adialog.R;
import cn.itsite.adialog.common.BaseViewHolder;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 加载中loading。
 */
public class LoadingDialog extends BaseDialog {
    private TextView tvLoading;
    private String text = "";

    public LoadingDialog(@NonNull Context context) {
        super(context);
    }

    public LoadingDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adialog_loading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWidth(-2);
        setHeight(-2);
    }

    @Override
    public void convertView(BaseViewHolder holder, BaseDialog dialog) {
        super.convertView(holder, dialog);
        tvLoading = holder.getView(R.id.tv_loading);
        tvLoading.setText(R.string.loading_text);
    }

    public LoadingDialog setText(String s) {
        if (tvLoading != null) {
            tvLoading.setText(text = s);
        }
        return this;
    }
}
