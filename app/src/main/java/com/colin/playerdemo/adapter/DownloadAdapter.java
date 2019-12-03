package com.colin.playerdemo.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.VideoBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class DownloadAdapter extends BaseQuickAdapter<VideoBean, BaseViewHolder> {
    public DownloadAdapter(@Nullable List<VideoBean> data) {
        super(R.layout.item_mine_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        Glide.with(mContext).load(item.getUrl()).into((RoundImageView) helper.getView(R.id.mine_image));
        helper.setText(R.id.introduce_tv,item.getName());
    }
}
