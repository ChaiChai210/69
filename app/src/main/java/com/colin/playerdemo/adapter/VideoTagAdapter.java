package com.colin.playerdemo.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Class_activity;
import com.colin.playerdemo.activity.PlayActivity;
import com.colin.playerdemo.bean.AloneBean;
import com.colin.playerdemo.bean.PlayDetailBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class VideoTagAdapter extends BaseQuickAdapter<PlayDetailBean.TagListBean, BaseViewHolder> {
    public VideoTagAdapter(@Nullable List<PlayDetailBean.TagListBean> data) {
        super(R.layout.item_tag_video, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PlayDetailBean.TagListBean item) {
        helper.setText(R.id.item_Tv, item.getName());

        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Class_activity.class).putExtra("name",item.getName())));
    }
}
