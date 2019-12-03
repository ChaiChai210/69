package com.colin.playerdemo.adapter;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.MineUserInfoBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.List;

public class HistoryRecordAdapter extends BaseQuickAdapter<MineUserInfoBean.RecordstlistBean, BaseViewHolder> {
    public HistoryRecordAdapter(@Nullable List<MineUserInfoBean.RecordstlistBean> data) {
        super(R.layout.item_mine_adapter,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineUserInfoBean.RecordstlistBean item) {
        Glide.with(mContext).load(item.getCover()).into((RoundImageView) helper.getView(R.id.mine_image));
        helper.setText(R.id.introduce_tv,item.getName());
    }
}
