package com.colin.playerdemo.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.MineUserInfoBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class MyFavoriteAdapter extends BaseQuickAdapter<MineUserInfoBean.CollectlistBean, BaseViewHolder> {
    public MyFavoriteAdapter(@Nullable List<MineUserInfoBean.CollectlistBean> data) {
        super(R.layout.item_mine_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineUserInfoBean.CollectlistBean item) {
        Glide.with(mContext).load(item.getCover()).into((RoundImageView) helper.getView(R.id.mine_image));
        helper.setText(R.id.introduce_tv,item.getName());
    }
}
