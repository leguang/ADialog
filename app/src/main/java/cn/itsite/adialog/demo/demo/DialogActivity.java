package cn.itsite.adialog.demo.demo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.adialog.ADialogListener;
import cn.itsite.adialog.BaseDialog;
import cn.itsite.adialog.BaseDialogFragment;
import cn.itsite.adialog.BaseViewHolder;
import cn.itsite.adialog.demo.R;


public class DialogActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt0, bt1, bt2, bt3, bt4, bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        initView();
        initData();
    }

    private void initView() {
        bt0 = (Button) findViewById(R.id.share);
        bt1 = (Button) findViewById(R.id.selector);
        bt2 = (Button) findViewById(R.id.edit_input);
        bt3 = (Button) findViewById(R.id.lucky_money);
        bt4 = (Button) findViewById(R.id.loading);
        bt5 = (Button) findViewById(R.id.tips);
    }

    private void initData() {
        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
                new BaseDialog(this)
                        .setLayoutId(R.layout.share_layout)//传入你的xml布局。
                        .setConvertListener(new ADialogListener.OnDialogConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, final Dialog dialog) {
                                //通过ViewHolder对View进行一些定制化。
                                holder.setOnClickListener(R.id.wechat, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        showToast("点击关闭");
                                        dialog.dismiss();
                                    }
                                });
                            }
                        })
                        .setDimAmount(0.3f)//设置window的暗度。
                        .setGravity(Gravity.TOP)//位置有多种选择。
                        .setAnimStyle(R.style.SlideAnimation)//进入和退出动画。
                        .show();//显示。
                break;
            case R.id.selector:
                final SelectorAdapter selectorAdapter = new SelectorAdapter();
                new BaseDialog(this)
                        .setLayoutId(R.layout.selector_layout)
                        .setConvertListener(new ADialogListener.OnDialogConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, final Dialog dialog) {
                                holder.setText(R.id.tv_title_selector_dialog_fragment, "请选择XXX")
                                        .setOnClickListener(R.id.iv_cancel_selector_dialog_fragment, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setOnClickListener(R.id.tv_cancel_selector_dialog_fragment, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.dismiss();
                                            }
                                        });
                                RecyclerView recyclerView = holder.getView(R.id.recyclerView_selector_dialog_fragment);
                                recyclerView.setLayoutManager(new LinearLayoutManager(DialogActivity.this));
                                recyclerView.setAdapter(selectorAdapter);
                                selectorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                        dialog.dismiss();
                                        showToast(position + "");
                                    }
                                });
                                selectorAdapter.setNewData(getData());
                            }
                        })
                        .setDimAmount(0.3f)
                        .setHeight(350)
                        .setGravity(Gravity.BOTTOM)
                        .setAnimStyle(R.style.SlideAnimation)
                        .show();
                break;
            case R.id.edit_input:
                new BaseDialog(this)
                        .setLayoutId(R.layout.commit_layout)
                        .setConvertListener(new ADialogListener.OnDialogConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, final Dialog dialog) {
                                final EditText editText = holder.getView(R.id.edit_input);
                                editText.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.showSoftInput(editText, 0);
                                    }
                                });

                                holder.setOnClickListener(R.id.commit, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showToast("点了");
                                    }
                                });
                            }
                        })
                        .setDimAmount(0.3f)
                        .setGravity(Gravity.LEFT)
                        .setAnimStyle(R.style.SlideAnimation)
                        .show();
                break;
            case R.id.lucky_money:
                new BaseDialog(this)
                        .setLayoutId(R.layout.ad_layout)
                        .setConvertListener(new ADialogListener.OnDialogConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, final Dialog dialog) {
                                holder.setOnClickListener(R.id.close, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showToast("点了");
                                        dialog.dismiss();
                                    }
                                });
                            }
                        })
                        .setGravity(Gravity.CENTER)
                        .setAnimStyle(R.style.SlideAnimation)
                        .show();
                break;
            case R.id.loading:
                new BaseDialogFragment()
                        .setLayoutId(R.layout.loading_layout)
                        .setWidth(100)
                        .setHeight(100)
                        .show(getSupportFragmentManager());
                break;
            case R.id.tips:
                BaseDialog dialog = new BaseDialog(this)
                        .setLayoutId(R.layout.confirm_layout)
                        .setConvertListener(new ADialogListener.OnDialogConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, final Dialog dialog) {
                                holder.setText(R.id.title, "提示")
                                        .setText(R.id.message, "您已支付成功！")
                                        .setOnClickListener(R.id.cancel, new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                showToast("点了");
                                                dialog.dismiss();
                                            }
                                        }).setOnClickListener(R.id.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showToast("点了");
                                        dialog.dismiss();
                                    }
                                });
                            }
                        })
                        .setDimAmount(0.3f)
                        .setMargin(60);
                dialog.setCancelable(false);
                dialog.show();
                break;
        }
    }

    private List<DataBean> getData() {
        List<DataBean> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(new DataBean("第" + i + "个", "请选择"));
        }
        return data;
    }

    public static Toast mToast;

    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
}
