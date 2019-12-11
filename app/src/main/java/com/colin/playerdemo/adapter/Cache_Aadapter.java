package com.colin.playerdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Cache_Activity;
import com.colin.playerdemo.bean.VideoBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Cache_Aadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Boolean show =false;
    private List<VideoBean> videoBeans;
    ItemOnclickListener itemOnclickListener;

    public void setItemOnclickListener(ItemOnclickListener itemOnclickListener) {
        this.itemOnclickListener = itemOnclickListener;
    }

    public void setVideoBeans(List<VideoBean> videoBeans) {
        this.videoBeans.clear();
        this.videoBeans.addAll(videoBeans);
        notifyDataSetChanged();
    }

    public Cache_Aadapter() {
        super();
        this.videoBeans = new ArrayList<>();
    }

    public void setBooleanslsit(Boolean show) {
       this.show = show;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Cache_AadapterHolder aadapterHolder = new Cache_AadapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_cache, parent, false));
        return aadapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cache_AadapterHolder aadapterHolder = (Cache_AadapterHolder) holder;
        aadapterHolder.showCache_AadapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return Cache_Activity.booleanslsit.size();
    }

    public class Cache_AadapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cache_checkBox)
        CheckBox cacheCheckBox;
        @BindView(R.id.cache_image)
        RoundImageView cacheImage;
        @BindView(R.id.video_name)
        TextView videoName;
        @BindView(R.id.size_tv)
        TextView sizeTv;
        public Cache_AadapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void showCache_AadapterHolder(final int position) {
            Glide.with(itemView.getContext()).load(videoBeans.get(position).getUrl()).into(cacheImage);
            videoName.setText(videoBeans.get(position).getName()+"");
            sizeTv.setText("已缓存 | "+getDataSize(Long.valueOf(videoBeans.get(position).getLength())));
            if(show){
                cacheCheckBox.setVisibility(View.VISIBLE);
                cacheCheckBox.setChecked(Cache_Activity.booleanslsit.get(position));
                cacheCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if(buttonView.isPressed()){
                        Cache_Activity.booleanslsit.set(position,!Cache_Activity.booleanslsit.get(position));
                    }
                });
            }else {
                cacheCheckBox.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(v -> itemOnclickListener.onclick(position));
        }
    }
    public static String getDataSize(long var0) {
        DecimalFormat var2 = new DecimalFormat("###.00");
        return var0 < 1024L ? var0 + "bytes" : (var0 < 1048576L ? var2.format((double) ((float) var0 / 1024.0F))
                + "KB" : (var0 < 1073741824L ? var2.format((double) ((float) var0 / 1024.0F / 1024.0F))
                + "MB" : (var0 > 0L ? var2.format((double) ((float) var0 / 1024.0F / 1024.0F / 1024.0F))
                + "GB" : "error")));
    }
    public interface ItemOnclickListener{
        void onclick(int position);
    }
}
