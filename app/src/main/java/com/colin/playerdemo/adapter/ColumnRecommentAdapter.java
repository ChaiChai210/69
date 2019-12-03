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

public class ColumnRecommentAdapter extends BaseQuickAdapter<Column_Bean.TjtopicBean, BaseViewHolder> {
    public ColumnRecommentAdapter(@Nullable List<Column_Bean.TjtopicBean> data) {
        super(R.layout.column_recomment_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Column_Bean.TjtopicBean item) {
        Glide.with(mContext).load(item.getPic()).into((RoundImageView) helper.getView(R.id.column_image));
        helper.setText(R.id.class_name_tv,item.getName());
        helper.setText(R.id.time_tv,item.getCreate_date());
        helper.setText(R.id.content_tv,item.getIntroduce());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Alone_Activity.class).
                putExtra("id",item.getId()+"")));
    }
}
