package com.colin.playerdemo.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.MainBean;
import com.colin.playerdemo.utils.StringUtils;
import com.colin.playerdemo.utils.UIhelper;

import java.util.List;

public class HomeBannerAdapter extends BaseQuickAdapter<MainBean.AdChartBean, BaseViewHolder> {
    public HomeBannerAdapter(@Nullable List<MainBean.AdChartBean> data) {
        super(R.layout.item_home_banner, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.AdChartBean item) {
        Glide.with(mContext).load(item.getPic()).into((ImageView) helper.getView(R.id.image));
//        helper.setText(R.id.iv_pic,item.getOwner());
//        helper.setText(R.id.tv_time,item.getTime());
        ImageView img = helper.getView(R.id.image);
        img.setOnClickListener(v -> {
            if (!StringUtils.isEmpty(item.getUrl())) {
                UIhelper.addClickAdRecord(mContext, item.getId());
                Uri uri = Uri.parse(item.getUrl());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);

            }
        });
    }
}
