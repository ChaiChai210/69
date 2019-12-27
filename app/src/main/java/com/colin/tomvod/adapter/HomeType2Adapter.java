package com.colin.tomvod.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.PlayActivity;
import com.colin.tomvod.bean.MainBean;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.List;

public class HomeType2Adapter extends BaseQuickAdapter<MainBean.VideoBean.ListBean, BaseViewHolder> {
    public HomeType2Adapter(@Nullable List<MainBean.VideoBean.ListBean> data) {
        super(R.layout.home_item_2,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.VideoBean.ListBean item) {
        Glide.with(mContext).load(item.getCover()).placeholder(R.mipmap.pl_home_222320).into((RoundImageView) helper.getView(R.id.home_item_1_image));
        helper.setText(R.id.home_item_1_tv,item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, PlayActivity.class).
                putExtra("id",item.getId()+"")));
    }
}
