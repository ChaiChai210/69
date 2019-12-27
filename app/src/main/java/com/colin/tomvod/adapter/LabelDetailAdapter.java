package com.colin.tomvod.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.bean.LabelBean;

import java.util.List;

public class LabelDetailAdapter extends BaseQuickAdapter<LabelBean, BaseViewHolder> {
    public LabelDetailAdapter(@Nullable List<LabelBean> data) {
        super(R.layout.item_lable_detail, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabelBean item) {
        helper.setText(R.id.content_tv, item.getName());
//        TextView content = helper.getView(R.id.content_tv);

//        if (item.isSelected()) {
//            Drawable dra = mContext.getResources().getDrawable(R.mipmap.icon_choosed);
//            dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
//            content.setCompoundDrawables(dra, null, null, null);
//            content.setCompoundDrawablePadding(5);
//        } else {
//            content.setCompoundDrawables(null, null, null, null);
//            content.setCompoundDrawablePadding(5);
//        }
    }
}
