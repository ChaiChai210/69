package com.colin.tomvod.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.PlayActivity;
import com.colin.tomvod.bean.SearchBean;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.List;

public class VideoClassifyAdapter extends BaseQuickAdapter<SearchBean, BaseViewHolder> {
    public VideoClassifyAdapter(@Nullable List<SearchBean> data) {
        super(R.layout.item_video_classify,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchBean item) {
        Glide.with(mContext).load(item.getCover()).into((RoundImageView) helper.getView(R.id.class_image));
        helper.setText(R.id.class_tv,item.getScore());
        helper.setText(R.id.introduce_tv,item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, PlayActivity.class).
                putExtra("id",item.getId()+"")));
    }
}
