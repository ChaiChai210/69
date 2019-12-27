package com.colin.tomvod.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.AnnouncementActivity;
import com.colin.tomvod.bean.Noticebean;

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
