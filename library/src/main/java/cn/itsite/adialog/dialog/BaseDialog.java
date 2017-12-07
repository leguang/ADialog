package cn.itsite.adialog.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatDialog;
import android.view.Window;
import android.view.WindowManager;

import cn.itsite.adialog.ADialogListener;
import cn.itsite.adialog.BaseViewHolder;
import cn.itsite.adialog.Utils;

public class BaseDialog extends AppCompatDialog {
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String GRAVITY = "gravity";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";
    private int margin;//左右边距
    private int width = -1;//宽度
    private int height = -2;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private int gravity;//是否底部显示
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;
    private ADialogListener.OnDialogConvertListener mConvertListener;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            gravity = savedInstanceState.getInt(GRAVITY);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
        layoutId = getLayoutId();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutId);
        convertView(new BaseViewHolder(getWindow().getDecorView()), this);
    }

    @NonNull
    @Override
    public Bundle onSaveInstanceState() {
        Bundle bundle = super.onSaveInstanceState();
        bundle.putInt(MARGIN, margin);
        bundle.putInt(WIDTH, width);
        bundle.putInt(HEIGHT, height);
        bundle.putFloat(DIM, dimAmount);
        bundle.putInt(GRAVITY, gravity);
        bundle.putInt(ANIM, animStyle);
        bundle.putInt(LAYOUT, layoutId);
        return bundle;
    }

    @Override
    public void onStart() {
        super.onStart();
        initWindow();
    }

    private void initWindow() {
        Window window = getWindow();
        if (window != null) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5F
            lp.dimAmount = dimAmount;
            lp.gravity = gravity;
            //设置dialog宽度
            if (margin > 0) {
                lp.width = Utils.getScreenWidth(getContext()) - 2 * Utils.dp2px(getContext(), margin);
            } else if (width > 0) {
                lp.width = Utils.dp2px(getContext(), width);
            } else {
                lp.width = width;
            }
            //设置dialog高度
            if (margin > 0) {
                lp.height = Utils.getScreenHeight(getContext()) - 2 * Utils.dp2px(getContext(), margin);
            } else if (width > 0) {
                lp.height = Utils.dp2px(getContext(), height);
            } else {
                lp.height = height;
            }
            window.setAttributes(lp);
        }
    }

    public void convertView(BaseViewHolder holder, BaseDialog dialog) {
        if (mConvertListener != null) {
            mConvertListener.convert(holder, dialog);
        }
    }

    public int getLayoutId() {
        return layoutId;
    }

    public BaseDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public BaseDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BaseDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BaseDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public BaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public BaseDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public BaseDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public BaseDialog setConvertListener(ADialogListener.OnDialogConvertListener listener) {
        this.mConvertListener = listener;
        return this;
    }
}
