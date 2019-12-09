package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.AnnouncementActivity;
import com.colin.playerdemo.activity.PlayActivity;
import com.colin.playerdemo.bean.MessageBean;

import java.util.List;

public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {
    public MessageAdapter(@Nullable List<MessageBean> data) {
        super(R.layout.item_mesage, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {
        helper.setText(R.id.title_tv, item.getNickname());
        helper.setText(R.id.time_tv, item.getCreate_time());
        helper.setText(R.id.content_tv, item.getContent());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, PlayActivity.class).
                putExtra("id",item.getVid())));
    }
}
