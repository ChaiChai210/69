package com.colin.tomvod.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.PlayActivity;
import com.colin.tomvod.bean.StarInfoBean;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Star_Aadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StarInfoBean.VideoListBean> video_list;

    public void setVideo_list(List<StarInfoBean.VideoListBean> video_list) {
        this.video_list.clear();
        this.video_list.addAll(video_list);
        notifyDataSetChanged();
    }

    public Star_Aadapter() {
        super();
        this.video_list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Star_AadapterHolder star_aadapterHolder = new Star_AadapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.star_adapter, parent, false));
        return star_aadapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Star_AadapterHolder aadapterHolder = (Star_AadapterHolder) holder;
        aadapterHolder.showStar_AadapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return video_list.size();
    }

    public class Star_AadapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.star_image)
        RoundImageView starImage;
        @BindView(R.id.video_name_tv)
        TextView videoNameTv;
        @BindView(R.id.num_tv)
        TextView numTv;
        @BindView(R.id.minute_tv)
        TextView minuteTv;
        public Star_AadapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void showStar_AadapterHolder(final int position) {
            Glide.with(itemView.getContext()).load(video_list.get(position).getCover()).into(starImage);
            videoNameTv.setText(video_list.get(position).getName());
            numTv.setText(video_list.get(position).getPlay_count()+"万次播放量");
            minuteTv.setText(video_list.get(position).getScore()+"");
            itemView.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), PlayActivity.class).putExtra("id",video_list.get(position).getId()+"")));
        }
    }
}
