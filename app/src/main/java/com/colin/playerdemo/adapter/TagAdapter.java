package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Class_activity;

import java.util.List;

public class TagAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public TagAdapter(@Nullable List<String> data) {
        super(R.layout.item_tag, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_tag_name, item);
        helper.itemView.setOnClickListener(v -> {
            mContext.startActivity(new Intent(mContext, Class_activity.class).putExtra("name", item));
        });
    }
}
