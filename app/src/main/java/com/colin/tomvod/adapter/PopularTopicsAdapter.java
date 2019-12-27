package com.colin.tomvod.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.Alone_Activity;
import com.colin.tomvod.bean.HotBean;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.List;

public class PopularTopicsAdapter extends BaseQuickAdapter<HotBean, BaseViewHolder> {
    public PopularTopicsAdapter(@Nullable List<HotBean> data) {
        super(R.layout.item_popular_topics, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotBean item) {
        Glide.with(mContext).load(item.getPic()).into((RoundImageView) helper.getView(R.id.column_class_image));
        helper.setText(R.id.column_class_tv, item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Alone_Activity.class).
                putExtra("id", item.getId() + "")));
    }
}
