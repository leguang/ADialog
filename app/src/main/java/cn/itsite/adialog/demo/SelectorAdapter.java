package cn.itsite.adialog.demo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


/**
 * Created by leguang on 2017/8/15 0015.
 * Email：langmanleguang@qq.com
 */

public class SelectorAdapter extends BaseQuickAdapter<DataBean, BaseViewHolder> {

    public SelectorAdapter() {
        super(R.layout.item_rv_selector);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataBean item) {
        helper.setText(R.id.tv_name_item_rv_host_selector, item.name)
                .setText(R.id.tv_role_item_rv_host_selector, item.des)
                .setText(R.id.tv_current_item_rv_host_selector, item.des);
    }
}
