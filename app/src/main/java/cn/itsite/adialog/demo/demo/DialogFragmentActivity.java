package cn.itsite.adialog.demo.demo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.itsite.adialog.ADialogListener;
import cn.itsite.adialog.BaseViewHolder;
import cn.itsite.adialog.demo.R;
import cn.itsite.adialog.dialogfragment.BaseDialogFragment;
import cn.itsite.adialog.dialogfragment.SelectorDialogFragment;

public class DialogFragmentActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt0, bt1, bt2, bt3, bt4, bt5, bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogfragment);

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
        bt6 = (Button) findViewById(R.id.dialog_in_fragment);
    }

    private void initData() {
        bt0.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share:
                new BaseDialogFragment()
                        .setLayoutId(R.layout.share_layout)//传入你的xml布局。
                        .setConvertListener(new ADialogListener.OnDialogFragmentConvertListener() {
                            //通过ViewHolder对View进行一些定制化。
                            @Override
                            public void convert(BaseViewHolder holder, DialogFragment dialog) {
                                holder.setOnClickListener(R.id.wechat, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showToast("点了微信");
                                    }
                                });
                            }
                        })
                        .setDimAmount(0.3f)//设置window的暗度。
                        .setGravity(Gravity.BOTTOM)//位置有多种选择。
                        .setAnimStyle(R.style.SlideAnimation)//进入和退出动画。
                        .show(getSupportFragmentManager(), "MyBaseDialogFragment");//显示。
                break;
            case R.id.selector:
                final List<DataBean> mData = getData();
                new SelectorDialogFragment()
                        .setItemLayoutId(R.layout.item_rv_selector)
                        .setData(mData)
                        .setOnItemConvertListener(new ADialogListener.OnItemConvertListener() {
                            @Override
                            public void onItemConvert(BaseViewHolder holder, int position, Dialog dialog) {
                                DataBean item = mData.get(position);
                                holder.setText(R.id.tv_name_item_rv_host_selector, item.name)
                                        .setText(R.id.tv_role_item_rv_host_selector, item.des)
                                        .setText(R.id.tv_current_item_rv_host_selector, item.des);
                            }
                        })
                        .setOnItemClickListener(new ADialogListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View v, BaseViewHolder holder, int position, Dialog dialog) {
                                showToast("position-->" + position);
                                dialog.dismiss();
                            }
                        })
                        .setTitle("请选择XXX")
                        .setHeight(350)
                        .setAnimStyle(R.style.SlideAnimation)
                        .setGravity(Gravity.BOTTOM)
                        .show(getSupportFragmentManager());

//                final SelectorAdapter selectorAdapter = new SelectorAdapter();
//                new BaseDialogFragment()
//                        .setLayoutId(R.layout.adialog_selector)
//                        .setConvertListener(new ADialogListener.OnDialogFragmentConvertListener() {
//
//                            @Override
//                            public void convert(BaseViewHolder holder, final DialogFragment dialog) {
//                                holder.setText(R.id.tv_title_selector, "请选择XXX")
//                                        .setOnClickListener(R.id.iv_cancel_selector, new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                dialog.dismiss();
//                                            }
//                                        })
//                                        .setOnClickListener(R.id.tv_cancel_selector, new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v) {
//                                                dialog.dismiss();
//                                            }
//                                        });
//                                RecyclerView recyclerView = holder.getView(R.id.recyclerView_selector);
//                                recyclerView.setLayoutManager(new LinearLayoutManager(DialogFragmentActivity.this));
//                                recyclerView.setAdapter(selectorAdapter);
//                                selectorAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                                        dialog.dismiss();
//                                        showToast(position + "");
//                                    }
//                                });
//                                selectorAdapter.setNewData(getData());
//                            }
//                        })
//                        .setDimAmount(0.3f)
//                        .setHeight(350)
//                        .setGravity(Gravity.BOTTOM)
//                        .setAnimStyle(R.style.SlideAnimation)
//                        .show(getSupportFragmentManager());
                break;
            case R.id.edit_input:
                new BaseDialogFragment()
                        .setLayoutId(R.layout.commit_layout)
                        .setConvertListener(new ADialogListener.OnDialogFragmentConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, DialogFragment dialog) {
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
                        .setGravity(Gravity.BOTTOM)
                        .setAnimStyle(R.style.SlideAnimation)
                        .show(getSupportFragmentManager());
                break;
            case R.id.lucky_money:
                new BaseDialogFragment()
                        .setLayoutId(R.layout.ad_layout)
                        .setConvertListener(new ADialogListener.OnDialogFragmentConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, final DialogFragment dialog) {
                                holder.setOnClickListener(R.id.close, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        showToast("点了");
                                        dialog.dismiss();
                                    }
                                });
                            }
                        })
                        .setGravity(Gravity.TOP)
                        .setAnimStyle(R.style.SlideAnimation)
                        .show(getSupportFragmentManager());
                break;
            case R.id.loading:
                new BaseDialogFragment()
                        .setLayoutId(R.layout.loading_layout)
                        .setWidth(100)
                        .setHeight(100)
                        .show(getSupportFragmentManager());
                break;
            case R.id.tips:
                BaseDialogFragment dialog = new BaseDialogFragment()
                        .setLayoutId(R.layout.confirm_layout)
                        .setConvertListener(new ADialogListener.OnDialogFragmentConvertListener() {
                            @Override
                            public void convert(BaseViewHolder holder, final DialogFragment dialog) {
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
                        .setMargin(60)
                        .show(getSupportFragmentManager());
                dialog.setCancelable(false);
                break;

            case R.id.dialog_in_fragment:
                AlertDialog alertDialog = new AlertDialog.Builder(this)
                        .setTitle("注意：")
                        .setMessage("是否退出应用？")
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .setCancelable(false)
                        .create();

                new BaseDialogFragment()
                        .setDialog(alertDialog)
                        .setGravity(Gravity.TOP)
                        .show(getSupportFragmentManager());
                break;
            default:
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
