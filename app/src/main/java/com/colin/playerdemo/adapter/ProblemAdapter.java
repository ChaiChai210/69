package com.colin.playerdemo.adapter;

import android.content.Intent;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.AnnouncementActivity;
import com.colin.playerdemo.activity.ProblemDetailActivity;
import com.colin.playerdemo.bean.ProblemBean;

import java.util.List;

public class ProblemAdapter extends BaseQuickAdapter<ProblemBean, BaseViewHolder> {
    public ProblemAdapter(@Nullable List<ProblemBean> data) {
        super(R.layout.item_problem, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProblemBean item) {
        helper.setText(R.id.pro_tv, item.getName());
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, ProblemDetailActivity.class).
                putExtra("content", item.getContent())));
    }
}
