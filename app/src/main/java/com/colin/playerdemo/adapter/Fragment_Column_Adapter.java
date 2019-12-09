package com.colin.playerdemo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.Star_Activity;
import com.colin.playerdemo.bean.StarBean;
import com.colin.playerdemo.customeview.third.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_Column_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<StarBean> list;

    public void setList(List<StarBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public Fragment_Column_Adapter() {
        super();
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Fragment_Column_AdapterHolder adapterHolder = new Fragment_Column_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_column_adapter, parent, false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Fragment_Column_AdapterHolder adapterHolder = (Fragment_Column_AdapterHolder) holder;
        adapterHolder.showFragment_Column_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Fragment_Column_AdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.column_image)
        RoundImageView columnImage;
        @BindView(R.id.head_layout)
        RelativeLayout headLayout;
        @BindView(R.id.name_tv)
        TextView nameTv;
        @BindView(R.id.num_video_tv)
        TextView numVideoTv;
        @BindView(R.id.content_tv)
        TextView content_tv;
        @BindView(R.id.colum_h_rv)
        RecyclerView columHRv;
        Fragment_Column_Child_Adapter column_child_adapter;

        public Fragment_Column_AdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void showFragment_Column_AdapterHolder(final int position) {
            Glide.with(itemView.getContext()).load(list.get(position).getPortrait()).into(columnImage);
            nameTv.setText(list.get(position).getName());
            content_tv.setText(list.get(position).getIntroduce());
            numVideoTv.setText(list.get(position).getVideo_count() + "部电影");
            columHRv.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            column_child_adapter = new Fragment_Column_Child_Adapter();
            columHRv.setAdapter(column_child_adapter);
            column_child_adapter.setVideo_list(list.get(position).getVideo_list());
            itemView.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), Star_Activity.class)
                    .putExtra("id", list.get(position).getId() + "")));
        }
    }
}
