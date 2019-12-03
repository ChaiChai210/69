package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Alone_Activity;
import com.colin.playerdemo.bean.Column_Bean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class ColumnHotTopicAdapter extends BaseQuickAdapter<Column_Bean.HottopicBean, BaseViewHolder> {
    public ColumnHotTopicAdapter(@Nullable List<Column_Bean.HottopicBean> data) {
        super(R.layout.column_hot_topic_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Column_Bean.HottopicBean item) {
        Glide.with(mContext).load(item.getPic()).into((RoundImageView) helper.getView(R.id.column_class_image));
        helper.setText(R.id.column_class_tv,item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Alone_Activity.class).
                putExtra("id",item.getId()+"")));
    }
}
