package cn.itsite.adialog.support.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.itsite.adialog.R;
import cn.itsite.adialog.common.BaseViewHolder;
import cn.itsite.adialog.support.ADialogListener;

/**
 * Author：leguang on 2017/4/12 0009 14:23
 * Email：langmanleguang@qq.com
 * <p>
 * 选择器。
 */
public class SelectorDialog extends BaseDialog {
    private String title;
    private List mData;
    private RecyclerView.Adapter adapter;
    private int itemLayoutId;
    private ADialogListener.OnItemConvertListener itemConvertListener;
    private ADialogListener.OnItemClickListener itemClickListener;

    public SelectorDialog(@NonNull Context context) {
        super(context);
    }

    public SelectorDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected SelectorDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adialog_selector;
    }

    @Override
    public void convertView(BaseViewHolder holder, BaseDialog dialog) {
        super.convertView(holder, dialog);
        holder.setText(R.id.tv_title_selector, title)
                .setOnClickListener(R.id.iv_cancel_selector, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                })
                .setOnClickListener(R.id.tv_cancel_selector, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

        RecyclerView recyclerView = holder.getView(R.id.recyclerView_selector);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (adapter != null) {
            recyclerView.setAdapter(adapter);
        } else {
            recyclerView.setAdapter(adapter = new RecyclerView.Adapter<BaseViewHolder>() {
                @Override
                public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    return new BaseViewHolder(getLayoutInflater().inflate(itemLayoutId, parent, false));
                }

                @Override
                public void onBindViewHolder(final BaseViewHolder holder, final int position) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (itemClickListener != null) {
                                itemClickListener.onItemClick(v, holder, position, SelectorDialog.this);
                            }
                        }
                    });

                    if (itemConvertListener != null) {
                        itemConvertListener.onItemConvert(holder, position, SelectorDialog.this);
                    }
                }

                @Override
                public int getItemCount() {
                    return mData == null ? 0 : mData.size();
                }
            });
        }
    }

    public String getTitle() {
        return title;
    }

    public SelectorDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public List getData() {
        return mData;
    }

    public SelectorDialog setData(List mData) {
        this.mData = mData;
        return this;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public SelectorDialog setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public int getItemLayoutId() {
        return itemLayoutId;
    }

    public SelectorDialog setItemLayoutId(int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
        return this;
    }

    public ADialogListener.OnItemConvertListener getOnItemConvertListener() {
        return itemConvertListener;
    }

    public SelectorDialog setOnItemConvertListener(ADialogListener.OnItemConvertListener itemConvertListener) {
        this.itemConvertListener = itemConvertListener;
        return this;
    }

    public ADialogListener.OnItemClickListener getOnItemClickListener() {
        return itemClickListener;
    }

    public SelectorDialog setOnItemClickListener(ADialogListener.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        return this;
    }
}
