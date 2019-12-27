package com.colin.tomvod.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.bean.MainBean;

import java.util.List;

public class HomeClassifyAdapter extends BaseQuickAdapter<MainBean.TypeVideoBean, BaseViewHolder> {
    public HomeClassifyAdapter(@Nullable List<MainBean.TypeVideoBean> data) {
        super(R.layout.adapter_home_class, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean.TypeVideoBean item) {
        ImageView pic = helper.getView(R.id.class_iv);
        TextView name = helper.getView(R.id.class_tv);
        int size = mData.size();
        if (size < 8) {
            if (helper.getAdapterPosition() == size) {
                pic.setImageResource(R.mipmap.ic_all);
                name.setText("全部");
            } else {
                Glide.with(mContext).load(item.getPic()).into(pic);
                name.setText(item.getName());
            }
        } else {
            if (helper.getAdapterPosition() == 7) {
                pic.setImageResource(R.mipmap.ic_all);
                name.setText("全部");
            } else {
                Glide.with(mContext).load(item.getPic()).into(pic);
                name.setText(item.getName());
            }
//        helper.setText(R.id.iv_pic,item.getOwner());
//        helper.setText(R.id.tv_time,item.getTime());
        }
    }
}
