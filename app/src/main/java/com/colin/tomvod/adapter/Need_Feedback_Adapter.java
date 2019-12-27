package com.colin.tomvod.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.tomvod.R;
import com.colin.tomvod.bean.FeedBackBean;

import java.util.ArrayList;
import java.util.List;


public class Need_Feedback_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FeedBackBean> list;

    public void setList(List<FeedBackBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    ItemOnclikListener itemOnclikListener;
    private int itemClick = -1;

    public void setItemClick(int itemClick) {
        this.itemClick = itemClick;
        notifyDataSetChanged();
    }

    public void setItemOnclikListener(ItemOnclikListener itemOnclikListener) {
        this.itemOnclikListener = itemOnclikListener;
    }

    public Need_Feedback_Adapter() {
        super();
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Need_Feedback_AdapterHolder adapterHolder = new Need_Feedback_AdapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.need_feedback_adapter
        ,parent,false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Need_Feedback_AdapterHolder adapterHolder = (Need_Feedback_AdapterHolder) holder;
        adapterHolder.showNeed_Feedback_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Need_Feedback_AdapterHolder extends RecyclerView.ViewHolder{
        TextView fedd_tv;
        public Need_Feedback_AdapterHolder(View itemView) {
            super(itemView);
            fedd_tv = itemView.findViewById(R.id.fedd_tv);
        }
        void showNeed_Feedback_AdapterHolder(final int position){
            fedd_tv.setText(list.get(position).getName());
            fedd_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnclikListener.Onclick(position,list.get(position).getId()+"");
                }
            });
            if(itemClick ==position){
                fedd_tv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.circular_shade_20));
                fedd_tv.setTextColor(Color.parseColor("#ffffff"));
            }else {
                fedd_tv.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.circular_all_20));
                fedd_tv.setTextColor(Color.parseColor("#666666"));

            }
        }
    }
    public interface ItemOnclikListener {
        void Onclick(int position, String id);
    }
}
