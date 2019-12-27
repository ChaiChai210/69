package com.colin.tomvod.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.PlayActivity;
import com.colin.tomvod.bean.StarBean;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Column_Child_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<StarBean.VideoListBean> video_list;

    public void setVideo_list(List<StarBean.VideoListBean> video_list) {
        this.video_list.clear();
        this.video_list.addAll(video_list);
        notifyDataSetChanged();
    }

    public Fragment_Column_Child_Adapter() {
        super();
        this.video_list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Fragment_Column_Child_AdapterHolder adapterHolder = new Fragment_Column_Child_AdapterHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_column_child_adapter, parent, false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Fragment_Column_Child_AdapterHolder adapterHolder = (Fragment_Column_Child_AdapterHolder) holder;
        adapterHolder.showFragment_Column_Child_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return video_list.size();
    }

    public class Fragment_Column_Child_AdapterHolder extends RecyclerView.ViewHolder {
        ImageView column_child_image;
        TextView column_child_tv;

        public Fragment_Column_Child_AdapterHolder(View itemView) {
            super(itemView);
            column_child_image = itemView.findViewById(R.id.column_child_image);
            column_child_tv = itemView.findViewById(R.id.column_child_tv);
        }

        void showFragment_Column_Child_AdapterHolder(final int position) {
            Glide.with(itemView.getContext()).load(video_list.get(position).getCover()).into(column_child_image);
            column_child_tv.setText(video_list.get(position).getName());
            itemView.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), PlayActivity.class).putExtra("id", video_list.get(position).getId() + "")));
        }
    }
}
