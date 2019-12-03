package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Alone_Activity;
import com.colin.playerdemo.bean.StarBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class ColumnStarChildAdapter extends BaseQuickAdapter<StarBean.VideoListBean, BaseViewHolder> {
    public ColumnStarChildAdapter(@Nullable List<StarBean.VideoListBean> data) {
        super(R.layout.fragment_star_child_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StarBean.VideoListBean item) {
        Glide.with(mContext).load(item.getCover()).into((RoundImageView) helper.getView(R.id.column_child_image));
        helper.setText(R.id.column_child_tv,item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Alone_Activity.class).
                putExtra("id",item.getId()+"")));
    }
}
