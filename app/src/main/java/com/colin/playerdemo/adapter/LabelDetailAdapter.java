package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Alone_Activity;
import com.colin.playerdemo.bean.LabelBean;

import java.util.List;

public class LabelDetailAdapter extends BaseQuickAdapter<LabelBean, BaseViewHolder> {
    public LabelDetailAdapter(@Nullable List<LabelBean> data) {
        super(R.layout.item_lable_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabelBean item) {
        helper.setText(R.id.content_tv,item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Alone_Activity.class).
                putExtra("id",item.getId()+"")));
    }
}
