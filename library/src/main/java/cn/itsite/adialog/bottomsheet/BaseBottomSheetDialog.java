package cn.itsite.adialog.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import cn.itsite.adialog.R;
import cn.itsite.adialog.common.BaseViewHolder;
import cn.itsite.adialog.common.Utils;
import cn.itsite.adialog.support.ADialogListener;

public class BaseBottomSheetDialog extends BottomSheetDialog {
    public static final String TAG = BaseBottomSheetDialog.class.getName();
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String GRAVITY = "gravity";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";
    private static final String PEEKHEIGHT = "peekHeight";
    protected int margin;//左右边距
    protected int width = -1;//宽度
    protected int height = -2;//高度
    protected float dimAmount = 0.5F;//灰度深浅
    protected int gravity;//是否底部显示
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;
    protected ADialogListener.OnDialogConvertListener mConvertListener;
    protected Integer peekHeight;
    protected BottomSheetBehavior<FrameLayout> behavior;

    public BaseBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public BaseBottomSheetDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected BaseBottomSheetDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
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
            peekHeight = savedInstanceState.getInt(PEEKHEIGHT);
        }
        layoutId = getLayoutId();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layoutId);
        View decorView = getWindow().getDecorView();
        behavior = BottomSheetBehavior.from(decorView.findViewById(R.id.design_bottom_sheet));
        convertView(new BaseViewHolder(decorView), this);
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
        bundle.putInt(PEEKHEIGHT, peekHeight);
        return bundle;
    }

    @Override
    public void onStart() {
        super.onStart();
        initWindow();
        if (peekHeight != null && behavior != null) {
            behavior.setPeekHeight(peekHeight);
        }
    }

    private void initWindow() {
        Window window = getWindow();
        if (window != null) {
            //window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
            if (height > 0) {
                lp.height = Utils.dp2px(getContext(), height);
            } else {
                lp.height = height;
            }
            window.setAttributes(lp);
        }
    }

    public void convertView(BaseViewHolder holder, BaseBottomSheetDialog dialog) {
        if (mConvertListener != null) {
            mConvertListener.convert(holder, dialog);
        }
    }

    public int getLayoutId() {
        return layoutId;
    }

    public BaseBottomSheetDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    public BaseBottomSheetDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BaseBottomSheetDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BaseBottomSheetDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public BaseBottomSheetDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public BaseBottomSheetDialog setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public BaseBottomSheetDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    public BaseBottomSheetDialog setConvertListener(ADialogListener.OnDialogConvertListener listener) {
        this.mConvertListener = listener;
        return this;
    }

    public BaseBottomSheetDialog setPeekHeight(int peekHeight) {
        this.peekHeight = peekHeight;
        return this;
    }

    public void updatePeekHeight(int peekHeight) {
        if (behavior != null) {
            behavior.setPeekHeight(peekHeight);
        }
    }
}
