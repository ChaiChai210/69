package com.colin.tomvod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.colin.tomvod.R;
import com.colin.tomvod.bean.GeneraListBean;

import java.util.ArrayList;
import java.util.List;


public class My_Generalize_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GeneraListBean> listBeans;

    public void setListBeans(List<GeneraListBean> listBeans) {
        this.listBeans.clear();
        this.listBeans.addAll(listBeans);
        notifyDataSetChanged();
    }

    public My_Generalize_Adapter() {
        super();
        this.listBeans = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new My_Generalize_AdapterHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_generalize_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        My_Generalize_AdapterHolder adapterHolder = (My_Generalize_AdapterHolder) holder;
        adapterHolder.showMy_Generalize_AdapterHolder(position);
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class My_Generalize_AdapterHolder extends RecyclerView.ViewHolder {
        private TextView name_tv;
        private TextView phone_tv;
        private TextView time_tv;

        public My_Generalize_AdapterHolder(View itemView) {
            super(itemView);
            name_tv = itemView.findViewById(R.id.name_tv);
            phone_tv = itemView.findViewById(R.id.phone_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
        }

        void showMy_Generalize_AdapterHolder(int position) {
            name_tv.setText(listBeans.get(position).getNickname());
            phone_tv.setText(listBeans.get(position).getPhone());
            time_tv.setText(listBeans.get(position).getCreate_time());
        }
    }
}
