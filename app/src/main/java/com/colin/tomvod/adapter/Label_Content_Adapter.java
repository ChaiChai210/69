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
import com.colin.tomvod.bean.SearchBean;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Label_Content_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SearchBean> list;

    public void setList(List<SearchBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public Label_Content_Adapter() {
        super();
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Label_Content_AdapterHolder adapterHolder = new Label_Content_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.star_adapter, parent, false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Label_Content_AdapterHolder adapterHolder = (Label_Content_AdapterHolder) holder;
        adapterHolder.showLabel_Content_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Label_Content_AdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.star_image)
        RoundImageView starImage;
        @BindView(R.id.video_name_tv)
        TextView videoNameTv;
        @BindView(R.id.num_tv)
        TextView numTv;
        @BindView(R.id.minute_tv)
        TextView minuteTv;

        public Label_Content_AdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void showLabel_Content_AdapterHolder(final int position) {
            Glide.with(itemView.getContext()).load(list.get(position).getCover()).into(starImage);
            videoNameTv.setText(list.get(position).getName());
            minuteTv.setText(list.get(position).getScore());
            numTv.setText(list.get(position).getPlay_count() + "次播放量");
            itemView.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), PlayActivity.class).putExtra("id", list.get(position).getId() + "")));
        }
    }
}
