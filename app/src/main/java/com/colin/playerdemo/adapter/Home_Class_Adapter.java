package com.colin.playerdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.colin.playerdemo.R;
import com.colin.playerdemo.bean.MainBean;

import java.util.ArrayList;
import java.util.List;


public class Home_Class_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MainBean.TypeVideoBean> data;
    HomeClass_Listener homeClass_listener;

    public void setHomeClass_listener(HomeClass_Listener homeClass_listener) {
        this.homeClass_listener = homeClass_listener;
    }

    public void setClasslist(List<MainBean.TypeVideoBean> type_video) {
        this.data.clear();
        this.data.addAll(type_video);
        notifyDataSetChanged();
    }

    public Home_Class_Adapter() {
        super();
        this.data = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Home_Class_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_home_class, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Home_Class_AdapterHolder adapterHolder = (Home_Class_AdapterHolder) holder;
        adapterHolder.showHome_Class_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public class Home_Class_AdapterHolder extends RecyclerView.ViewHolder {
        TextView class_tv;
        ImageView class_iv;

        public Home_Class_AdapterHolder(View itemView) {
            super(itemView);
            class_tv = itemView.findViewById(R.id.class_tv);
            class_iv = itemView.findViewById(R.id.class_iv);
        }

        void showHome_Class_AdapterHolder(final int position) {
            if (data.size() < 8) {
                if (position == data.size()) {
                    class_iv.setImageResource(R.mipmap.ic_all);
                    class_tv.setText("全部");
                } else {
                    Glide.with(itemView.getContext()).load(data.get(position).getPic()).into(class_iv);
                    class_tv.setText(data.get(position).getName());
                }
            } else {
                if (position == 7) {
                    class_iv.setImageResource(R.mipmap.ic_all);
                    class_tv.setText("全部");
                } else {
                    Glide.with(itemView.getContext()).load(data.get(position).getPic()).into(class_iv);
                    class_tv.setText(data.get(position).getName());
                }

            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (class_tv.getText().toString().equals("全部")) {
                        homeClass_listener.Onclick(-1);
                    } else {
                        homeClass_listener.Onclick(position);
                    }

                }
            });
        }
    }

    public interface HomeClass_Listener {
        void Onclick(int position);
    }
}
