package com.colin.tomvod.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.Class_activity;
import com.colin.tomvod.bean.PlayDetailBean;

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
