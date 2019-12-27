package com.colin.tomvod.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.tomvod.R;
import com.colin.tomvod.activity.HistoryActivity;
import com.colin.tomvod.activity.PlayActivity;
import com.colin.tomvod.bean.HistoryBean;
import com.colin.tomvod.customeview.third.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_History_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Boolean show = false;
    private int from = 0;
    private List<HistoryBean.DataBean> data;

    public void setData(List<HistoryBean.DataBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public Fragment_History_Adapter() {
        super();
        this.data = new ArrayList<>();
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public void setBooleanslsit(Boolean show) {
        this.show = show;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Fragment_History_AdapterHolder adapterHolder = new Fragment_History_AdapterHolder(LayoutInflater.
                from(parent.getContext()).inflate(R.layout.adapter_cache, parent, false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Fragment_History_AdapterHolder adapterHolder = (Fragment_History_AdapterHolder) holder;
        adapterHolder.showFragment_History_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        if (HistoryActivity.historybooleanslsit.size() == 0) {
            return 0;
        } else {
            return HistoryActivity.historybooleanslsit.get(from).size();
        }
    }

    public class Fragment_History_AdapterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cache_checkBox)
        CheckBox cacheCheckBox;
        @BindView(R.id.cache_image)
        RoundImageView cacheImage;
        @BindView(R.id.video_name)
        TextView videoName;
        @BindView(R.id.size_tv)
        TextView sizeTv;

        public Fragment_History_AdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void showFragment_History_AdapterHolder(final int position) {
            Glide.with(itemView.getContext()).load(data.get(position).getCover()).into(cacheImage);
            sizeTv.setText(data.get(position).getCdate());
            videoName.setText(data.get(position).getName());
            if (show) {
                cacheCheckBox.setVisibility(View.VISIBLE);
                cacheCheckBox.setChecked(HistoryActivity.historybooleanslsit.get(from).get(position));
                cacheCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (buttonView.isPressed()) {
                            HistoryActivity.historybooleanslsit.get(from).set(position, !HistoryActivity.historybooleanslsit.get(from).get(position));
                        }
                    }
                });
            } else {
                cacheCheckBox.setVisibility(View.GONE);
            }
            itemView.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), PlayActivity.class).putExtra("id", data.get(position).getVid() + "")));
        }

    }
}
