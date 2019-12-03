package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.activity.PlayActivity;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.AloneBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class AloneAdapter extends BaseQuickAdapter<AloneBean.ListBean, BaseViewHolder> {
    public AloneAdapter(@Nullable List<AloneBean.ListBean> data) {
        super(R.layout.home_item_2,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AloneBean.ListBean item) {
        Glide.with(mContext).load(item.getCover()).into((RoundImageView) helper.getView(R.id.home_item_1_image));
        helper.setText(R.id.grade_tv,item.getScore());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, PlayActivity.class).
                putExtra("id",item.getId()+"")));
    }
}
