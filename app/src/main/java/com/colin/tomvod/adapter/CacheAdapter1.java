package com.colin.tomvod.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.Cache_Play_Activity;
import com.colin.tomvod.activity.PlayActivity;
import com.colin.tomvod.bean.AloneBean;
import com.colin.tomvod.bean.VideoDownLoad;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.List;

import butterknife.ButterKnife;

public class CacheAdapter1 extends BaseQuickAdapter<VideoDownLoad, BaseViewHolder> {
    private boolean edit;

    public void setEdit(boolean edit) {
        this.edit = edit;
        notifyDataSetChanged();
    }

    public CacheAdapter1(@Nullable List<VideoDownLoad> data) {
        super(R.layout.adapter_cache,data);
    }


    @Override
    protected void convert(BaseViewHolder helper, VideoDownLoad item) {
        RoundImageView cache_image = helper.getView(R.id.cache_image);
        Glide.with(mContext).load(item.getUrl()).into(cache_image);

        helper.setText(R.id.video_name,item.getName());
        CheckBox checkBox = helper.getView(R.id.cache_checkBox);
        if(item.isSelected()){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
        if(edit){
            checkBox.setVisibility(View.VISIBLE);
        }else {
            checkBox.setVisibility(View.GONE);
        }
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                item.setSelected(true);
            }else {
                item.setSelected(false);
            }
        });
        helper.itemView.setOnClickListener(v -> mContext.startActivity(new Intent(mContext, Cache_Play_Activity.class).
                putExtra("id",item.getId())));
    }
}
