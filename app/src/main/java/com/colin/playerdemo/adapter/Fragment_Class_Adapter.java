package com.colin.playerdemo.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.activity.PlayActivity;
import com.colin.playerdemo.bean.SearchBean;

import java.util.ArrayList;
import java.util.List;


public class Fragment_Class_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SearchBean>list;

    public void setList(List<SearchBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public Fragment_Class_Adapter() {
        super();
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Fragment_Class_AdapterHolder adapterHolder = new Fragment_Class_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_classify,parent,false));
        return adapterHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Fragment_Class_AdapterHolder adapterHolder  = (Fragment_Class_AdapterHolder) holder;
        adapterHolder.showFragment_Class_AdapterHolder(position);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Fragment_Class_AdapterHolder extends RecyclerView.ViewHolder{
        ImageView class_image;
        TextView introduce_tv;
        TextView class_tv;
        public Fragment_Class_AdapterHolder(View itemView) {
            super(itemView);
            class_tv = itemView.findViewById(R.id.class_tv);
            introduce_tv = itemView.findViewById(R.id.introduce_tv);
            class_image = itemView.findViewById(R.id.class_image);
        }
        void showFragment_Class_AdapterHolder(final int position){
            Glide.with(itemView.getContext()).load(list.get(position).getCover()).into(class_image);
            introduce_tv.setText(list.get(position).getName());
            class_tv.setText(list.get(position).getScore()+"");
            itemView.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), PlayActivity.class).putExtra("id",list.get(position).getId()+"")));
        }
    }
}
