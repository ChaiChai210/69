package com.colin.playerdemo.adapter;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.AnnouncementActivity;
import com.colin.playerdemo.activity.PlayActivity;
import com.colin.playerdemo.bean.AloneBean;
import com.colin.playerdemo.bean.Noticebean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class AnnouncementAdapter extends BaseQuickAdapter<Noticebean, BaseViewHolder> {
    public AnnouncementAdapter(@Nullable List<Noticebean> data) {
        super(R.layout.item_announcement, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Noticebean item) {
        helper.setText(R.id.title_tv, item.getName());
        helper.setText(R.id.time_tv, item.getCreate_date());
        helper.setText(R.id.content_tv, item.getContent());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, AnnouncementActivity.class).
                putExtra("content", item.getContent())));
    }
}
