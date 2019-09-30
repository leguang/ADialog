package cn.itsite.adialog.demo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.adialog.bottomsheet.BaseBottomSheetDialog;
import cn.itsite.adialog.bottomsheet.BaseBottomSheetDialogFragment;
import cn.itsite.adialog.common.BaseViewHolder;
import cn.itsite.adialog.support.ADialogListener;

public class BottomSheetActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        initView();
    }

    private void initView() {
        findViewById(R.id.bottom_sheet_dialog)
                .setOnClickListener(v -> {
                    showBottomSheetDialog();
                });
        findViewById(R.id.bottom_sheet_dialog_fragment)
                .setOnClickListener(v -> {
                    showBottomSheetDialogFragment();
                });
    }

    private void showBottomSheetDialog() {
        final List<DataBean> mData = getData();

        new BaseBottomSheetDialog(this)
                .setLayoutId(R.layout.adialog_selector)//传入你的xml布局。
                .setConvertListener(new ADialogListener.OnDialogConvertListener() {
                    @Override
                    public void convert(BaseViewHolder holder, final Dialog dialog) {
                        holder.setText(R.id.tv_title_selector, "标题")
                                .setOnClickListener(R.id.iv_cancel_selector, v -> dialog.dismiss())
                                .setOnClickListener(R.id.tv_cancel_selector, v -> dialog.dismiss());

                        RecyclerView recyclerView = holder.getView(R.id.recyclerView_selector);
                        recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
                        if (adapter != null) {
                            recyclerView.setAdapter(adapter);
                        } else {
                            recyclerView.setAdapter(adapter = new RecyclerView.Adapter<BaseViewHolder>() {
                                @Override
                                public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                    return new BaseViewHolder(getLayoutInflater().inflate(R.layout.item_rv_selector, parent, false));
                                }

                                @Override
                                public void onBindViewHolder(final BaseViewHolder holder, final int position) {
                                    holder.itemView.setOnClickListener(v -> showToast("1111"));

                                }

                                @Override
                                public int getItemCount() {
                                    return mData == null ? 0 : mData.size();
                                }
                            });
                        }
                    }
                })
                .setDimAmount(0.3F)//设置window的暗度。
                .show();//显示。
    }

    private void showBottomSheetDialogFragment() {
        final List<DataBean> mData = getData();

        new BaseBottomSheetDialogFragment()
                .setLayoutId(R.layout.adialog_selector)//传入你的xml布局。
                .setConvertListener(new ADialogListener.OnDialogFragmentConvertListener() {

                    @Override
                    public void convert(BaseViewHolder holder, DialogFragment dialog) {
                        holder.setText(R.id.tv_title_selector, "标题")
                                .setOnClickListener(R.id.iv_cancel_selector, v -> dialog.dismiss())
                                .setOnClickListener(R.id.tv_cancel_selector, v -> dialog.dismiss());

                        RecyclerView recyclerView = holder.getView(R.id.recyclerView_selector);
                        recyclerView.setLayoutManager(new LinearLayoutManager(dialog.getContext()));
                        if (adapter != null) {
                            recyclerView.setAdapter(adapter);
                        } else {
                            recyclerView.setAdapter(adapter = new RecyclerView.Adapter<BaseViewHolder>() {
                                @Override
                                public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                    return new BaseViewHolder(getLayoutInflater().inflate(R.layout.item_rv_selector, parent, false));
                                }

                                @Override
                                public void onBindViewHolder(final BaseViewHolder holder, final int position) {
                                    holder.itemView.setOnClickListener(v -> showToast("1111"));

                                }

                                @Override
                                public int getItemCount() {
                                    return mData == null ? 0 : mData.size();
                                }
                            });
                        }
                    }
                })
                .show(getSupportFragmentManager());//显示。
    }

    public Toast mToast;

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    private List<DataBean> getData() {
        List<DataBean> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new DataBean("第" + i + "个", "请选择"));
        }
        return data;
    }
}
