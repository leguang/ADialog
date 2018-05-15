package cn.itsite.adialog.support.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cn.itsite.adialog.support.ADialogListener;
import cn.itsite.adialog.support.dialog.SelectorDialog;

public class SelectorDialogFragment extends BaseDialogFragment {
    private String title;
    private List mData;
    private RecyclerView.Adapter adapter;
    private int itemLayoutId;
    private ADialogListener.OnItemConvertListener itemConvertListener;
    private ADialogListener.OnItemClickListener itemClickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new SelectorDialog(getContext())
                .setTitle(title)
                .setItemLayoutId(itemLayoutId)
                .setData(mData)
                .setAdapter(adapter)
                .setOnItemConvertListener(itemConvertListener)
                .setOnItemClickListener(itemClickListener);
    }

    public String getTitle() {
        return title;
    }

    public SelectorDialogFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    public List getData() {
        return mData;
    }

    public SelectorDialogFragment setData(List mData) {
        this.mData = mData;
        return this;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public SelectorDialogFragment setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public int getItemLayoutId() {
        return itemLayoutId;
    }

    public SelectorDialogFragment setItemLayoutId(int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
        return this;
    }

    public ADialogListener.OnItemConvertListener getOnItemConvertListener() {
        return itemConvertListener;
    }

    public SelectorDialogFragment setOnItemConvertListener(ADialogListener.OnItemConvertListener itemConvertListener) {
        this.itemConvertListener = itemConvertListener;
        return this;
    }

    public ADialogListener.OnItemClickListener getOnItemClickListener() {
        return itemClickListener;
    }

    public SelectorDialogFragment setOnItemClickListener(ADialogListener.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        return this;
    }
}
